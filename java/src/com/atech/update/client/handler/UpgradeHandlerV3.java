package com.atech.update.client.handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.swing.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.atech.i18n.I18nControlAbstract;
import com.atech.update.client.UpdateDialog;
import com.atech.update.config.UpdateConfiguration;
import com.atech.update.config.UpdateConfigurationXml;
import com.atech.update.v3.defs.UpdateAction;
import com.atech.update.v3.dto.UpdateNotesDTO;
import com.atech.update.v3.xml.UpdateServerResponse;
import com.atech.utils.ATDataAccessAbstract;

/**
 * Created by andy on 15.03.15.
 */
public class UpgradeHandlerV3 implements UpgradeHandlerInterface
{

    private static final Log log = LogFactory.getLog(UpgradeHandlerV3.class);

    ATDataAccessAbstract dataAccess;
    UpdateDialog updateDialog;
    I18nControlAbstract i18nControl;
    UpdateConfiguration updateConfiguration;
    String serverName;

    private static UpgradeHandlerV3 staticHandler;


    public static UpgradeHandlerV3 createInstance(UpdateDialog updDialog, UpdateConfiguration updateConfiguration,
            ATDataAccessAbstract dataAccess)
    {
        if (staticHandler == null)
        {
            staticHandler = new UpgradeHandlerV3(updDialog, updateConfiguration, dataAccess);
        }

        return staticHandler;
    }


    public static UpgradeHandlerV3 getInstance()
    {
        if (staticHandler == null)
        {
            throw new InstantiationError();
        }

        return staticHandler;
    }


    private UpgradeHandlerV3(UpdateDialog updDialog, UpdateConfiguration updateConfiguration,
            ATDataAccessAbstract dataAccess)
    {
        this.dataAccess = dataAccess;
        this.i18nControl = dataAccess.getI18nControlInstance();
        this.updateDialog = updDialog;
        this.updateConfiguration = updateConfiguration;

        // Server Name
        serverName = "http://localhost:8060/ATechUpdateSystemV3";
    }


    public void checkServer()
    {
        if (!dataAccess.getDeveloperMode())
        {
            JOptionPane.showMessageDialog(this.updateDialog, i18nControl.getMessage("UPDATE_SERVER_NA"),
                i18nControl.getMessage("INFORMATION"), JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try
        {

            String server_name = this.updateDialog.getUpdateSettings().update_server;

            // this is testing only
            long current_version = 4;
            long current_db = 7;
            // http://localhost:8070/ATechUpdateSystemV3?application=ggc&version=11&action=get_info

            String iLine2;
            // this.updateConfiguration.db_update_site

            server_name = "http://localhost:8060/ATechUpdateSystemV3";

            UpdateServerResponse updateServerResponse = getInfo(serverName);

            if (updateServerResponse != null)

            {
                if (updateServerResponse.errorResponse != null)
                {
                    this.updateDialog.setStatusText(i18nControl
                            .getMessage(updateServerResponse.errorResponse.errorDescriptionKey));
                }
                else
                {
                    if (updateServerResponse.successResponse.foundNewVersion)
                    {
                        if (updateServerResponse.notesCount > 0)
                        {
                            this.updateDialog
                                    .setStatusText(String.format(
                                        i18nControl.getMessage("ATUS_FOUND_NEW_VERSION_NOTES"),
                                        updateServerResponse.notesCount));
                            this.updateDialog.getBtnNotes().setEnabled(true);
                        }
                        else
                        {
                            this.updateDialog.setStatusText(i18nControl.getMessage("ATUS_FOUND_NEW_VERSION"));
                        }

                        UpdateConfigurationXml uconfXml = getUpdateXml(serverName, updateServerResponse);

                        this.updateDialog.getModel().updateModel(uconfXml.getUpdateConfiguration());
                        this.updateDialog.getModel().fireTableDataChanged();
                    }
                    else
                    {
                        if (updateServerResponse.notesCount > 0)
                        {
                            this.updateDialog.setStatusText(String.format(
                                i18nControl.getMessage("ATUS_NO_NEW_VERSION_NOTES"), updateServerResponse.notesCount));
                            this.updateDialog.getBtnNotes().setEnabled(true);
                        }
                        else
                        {
                            this.updateDialog.setStatusText(i18nControl.getMessage("ATUS_NO_NEW_VERSION"));
                        }
                    }
                }
            }

        }
        catch (Exception ex)
        {
            this.updateDialog.setStatusText(i18nControl.getMessage("UPD_ERROR_CONTACTING_SERVER"));
            this.updateDialog.setStatusTooltip(ex.getLocalizedMessage());
        }

    }


    private UpdateServerResponse getInfo(String serverName) throws Exception
    {
        String dataRaw = null;

        {
            dataRaw = getServletData(serverName + //
                    "?action=" + UpdateAction.GetInfo.getUrlAction() + //
                    "&application=" + this.updateConfiguration.product_id + //
                    "&version=" + this.updateConfiguration.version_numeric);
        }

        if ((dataRaw != null) && (dataRaw.contains("<updateServerResponse>")))
        {

            String xml = dataRaw.substring(dataRaw.indexOf("<updateServerResponse>"),
                dataRaw.indexOf("</updateServerResponse>") + "</updateServerResponse>".length());

            return deserializeResponse(xml);
        }
        else
        {
            this.updateDialog.setStatusText(i18nControl.getMessage("INTERNAL_PROBLEM"));
            // internal error
            return null;
        }
    }


    public Object getNotes() throws Exception
    {
        // fixme
        String dataRaw = getServletData(serverName + //
                "?action=" + UpdateAction.GetNotes.getUrlAction() + //
                "&application=" + this.updateConfiguration.product_id + //
                "&version=" + this.updateConfiguration.version_numeric);

        if (dataRaw == null)
        {
            return null;
        }
        else
        {
            if (dataRaw.contains("<updateServerResponse>"))
            {

                String xml = dataRaw.substring(dataRaw.indexOf("<updateServerResponse>"),
                    dataRaw.indexOf("</updateServerResponse>") + "</updateServerResponse>".length());

                UpdateServerResponse response = this.deserializeResponse(dataRaw);
                return response;
            }
            else
            {
                UpdateNotesDTO notesDTO = deserializeNotes(dataRaw);
                return notesDTO;
            }

        }

    }


    private UpdateServerResponse deserializeResponse(String xml)
    {

        System.out.println("Found xml: " + xml);
        Serializer serializer = new Persister();

        StringReader reader = new StringReader(xml);

        try
        {
            UpdateServerResponse serverResponse = serializer.read(UpdateServerResponse.class, reader);

            return serverResponse;
        }
        catch (Exception ex)
        {
            this.updateDialog.setStatusText(i18nControl.getMessage("ATUS_ERROR_DECODING_DATA"));

            log.error("Error making response" + ex, ex);
            // error decoding
            return null;
        }
    }


    private UpdateNotesDTO deserializeNotes(String xml)
    {

        System.out.println("Found xml: " + xml);
        Serializer serializer = new Persister();

        StringReader reader = new StringReader(xml);

        try
        {
            UpdateNotesDTO notesDTO = serializer.read(UpdateNotesDTO.class, reader);
            return notesDTO;
        }
        catch (Exception ex)
        {
            this.updateDialog.setStatusText(i18nControl.getMessage("ATUS_ERROR_DECODING_DATA"));

            log.error("Error making response" + ex, ex);
            // error decoding
            return null;
        }
    }


    private UpdateConfigurationXml getUpdateXml(String serverName, UpdateServerResponse updateServerResponse)
    {

        String dataRaw = null;
        try
        {
            dataRaw = getServletData(serverName + //
                    "?action=" + UpdateAction.GetXmlData.getUrlAction() + //
                    "&application=" + this.updateConfiguration.product_id + //
                    "&version=" + updateServerResponse.successResponse.applicationUpdate.versionNumber);
        }
        catch (Exception e)
        {}

        if (dataRaw == null)
        {
            return null;
        }
        else
        {
            if (dataRaw.contains("<updateServerResponse>"))
            {
                String xml = dataRaw.substring(dataRaw.indexOf("<updateServerResponse>"),
                    dataRaw.indexOf("</updateServerResponse>") + "</updateServerResponse>".length());

                UpdateServerResponse serverResponse = deserializeResponse(xml);

                if (serverResponse != null)
                {
                    this.updateDialog.setStatusText(i18nControl
                            .getMessage(updateServerResponse.errorResponse.errorDescriptionKey));
                }

                return null;
            }
            else if (dataRaw.contains("<update_file>"))
            {
                UpdateConfigurationXml updateConfigurationXml = new UpdateConfigurationXml(dataRaw);

                return updateConfigurationXml;
            }
            else
            {
                this.updateDialog.setStatusText(i18nControl.getMessage("INTERNAL_PROBLEM"));
                return null;
            }
        }

    }


    /**
     * For Update
     * @param full_url
     * @return
     */
    private String getServletData(String full_url) throws Exception
    {
        try
        {
            URL url = new URL(full_url);

            log.debug("Servlet::URL (" + full_url + ")");

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String iLine, iLine2;
            iLine2 = "";

            while ((iLine = in.readLine()) != null)
            {
                iLine2 += iLine;
                // System.out.println(iLine);
            } // while file

            in.close();

            // else
            {
                System.out.println("Raw Data: " + iLine2);
                return iLine2;
            }

        }
        catch (Exception ex)
        {
            log.error("Servlet::URL (" + full_url + ")");
            log.error("Error contacting servlet: " + ex, ex);
            // ex.printStackTrace();
            this.updateDialog.setStatusText(i18nControl.getMessage("UPD_ERROR_CONTACTING_SERVER"));
            // dataAccess.showDialog(this.updateDialog,
            // ATDataAccessAbstract.DIALOG_ERROR,
            // s i18nControl.getMessage("UPD_ERROR_CONTACTING_SERVER"));

            throw ex;

            // System.out.println("Exception reading Web. Ex: " + ex);
            // return null;
        }
        // iLine2 = getServletData(server_name +
        // "ATechUpdateSystem?action=get_update_list&" + "product_id="
        // + dataAccess.getAppName() + "&" + "current_version=" +
        // current_version + "&" + "next_version="
        // + this.updateDialog.getNextVersion());

    }


    public void runUpdate()
    {
        try
        {
            String server_name = this.updateDialog.getUpdateSettings().update_server;
            long current_version = 4;
            // long current_db = 7;

            String iLine2 = null;

            // iLine2 = getServletData(server_name +
            // "ATechUpdateSystem?action=get_update_list&" + "product_id="
            // + dataAccess.getAppName() + "&" + "current_version=" +
            // current_version + "&" + "next_version="
            // + this.updateDialog.getNextVersion());

            if (iLine2 == null)
            {
                System.out.println("No data returned !");
                return;
            }

            String ret_msg = iLine2.substring(
                iLine2.indexOf("RETURN_DATA_START__") + "RETURN_DATA_START__<br>".length(),
                iLine2.indexOf("<br>__RETURN_DATA_END"));

            this.updateDialog.processDetailsData(ret_msg);

        }
        catch (Exception ex)
        {
            System.out.println("runUpdate(). Ex.: " + ex);
        }
        // dataAccess.showDialog(this, ATDataAccessAbstract.DIALOG_INFO,
        // "Run Update !");

    }
}