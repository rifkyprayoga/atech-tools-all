package com.atech.help;

import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Locale;

import javax.help.BadIDException;
import javax.help.CSH;
import javax.help.DefaultHelpBroker;
import javax.help.HelpSet;
import javax.help.InvalidHelpSetContextException;
import javax.help.MainWindow;
import javax.help.Map.ID;
import javax.help.Presentation;
import javax.help.WindowPresentation;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * This file is part of ATech Tools library.
 * 
 * <one line to give the library's name and a brief idea of what it does.>
 * Copyright (C) 2007 Andy (Aleksander) Rozman (Atech-Software)
 * 
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * 
 * For additional information about this project please visit our project site
 * on http://atech-tools.sourceforge.net/ or contact us via this emails:
 * andyrozman@users.sourceforge.net or andy@atech-software.com
 * 
 * @author Andy
 * 
 */

public class DummyHelpBroker extends DefaultHelpBroker
{

    private MainWindow mw = null;
    private HelpSet helpKeyHS = null;
    private String helpKeyPresentation = null;
    private String helpKeyPresentationName = null;

    /**
     * Constructor
     * 
     * @param hs
     */
    public DummyHelpBroker(HelpSet hs)
    {
        mw = (MainWindow) MainWindow.getPresentation(hs, null);
    }

    /**
     * Zero-argument constructor. It should be followed by a setHelpSet()
     * invocation.
     */
    public DummyHelpBroker()
    {
        mw = (MainWindow) MainWindow.getPresentation(null, null);
    }

    /**
     * Get the WindowPresentation for this HelpBroker
     * 
     * @returns the window presentation for this object
     */
    @Override
    public WindowPresentation getWindowPresentation()
    {
        return mw;
    }

    /**
     * Returns the default HelpSet
     */
    @Override
    public HelpSet getHelpSet()
    {
        return mw.getHelpSet();
    }

    /**
     * Changes the HelpSet for this broker.
     * 
     * @param hs
     *            The HelpSet to set for this broker. A null hs is valid
     *            parameter.
     */
    @Override
    public void setHelpSet(HelpSet hs)
    {
        debug("setHelpSet");
        mw.setHelpSet(hs);
    }

    /**
     * Set the presentation attributes from a HelpSet.Presentation. The
     * HelpSet.Presentation must be in the current HelpSet.
     * 
     * @param hsPres
     *            The HelpSet.Presentation
     * @since 2.0
     */
    @Override
    public void setHelpSetPresentation(HelpSet.Presentation hsPres)
    {
        debug("setHelpSetPresentation");
        mw.setHelpSetPresentation(hsPres);
    }

    /**
     * Gets the locale of this component.
     * 
     * @return This component's locale. If this component does not have a
     *         locale, the defaultLocale is returned.
     * @see #setLocale
     */
    @Override
    public Locale getLocale()
    {
        return mw.getLocale();
    }

    /**
     * Sets the locale of this HelpBroker. The locale is propagated to the
     * presentation.
     * 
     * @param l
     *            The locale to become this component's locale. A null locale is
     *            the same as the defaultLocale.
     * @see #getLocale
     */
    @Override
    public void setLocale(Locale l)
    {
        mw.setLocale(l);
    }

    /**
     * Gets the font for this HelpBroker.
     */
    @Override
    public Font getFont()
    {
        return mw.getFont();
    }

    /**
     * Sets the font for this this HelpBroker.
     * 
     * @param f
     *            The font.
     */
    @Override
    public void setFont(Font f)
    {
        mw.setFont(f);
    }

    /**
     * Set the currentView to the navigator with the same name as the
     * <tt>name</tt> parameter.
     * 
     * @param name
     *            The name of the navigator to set as the current view. If nav
     *            is null or not a valid Navigator in this HelpBroker then an
     *            IllegalArgumentException is thrown.
     * @throws IllegalArgumentException
     *             if nav is null or not a valid Navigator.
     */
    @Override
    public void setCurrentView(String name)
    {
        mw.setCurrentView(name);
    }

    /**
     * Determines the current navigator.
     */
    @Override
    public String getCurrentView()
    {
        return mw.getCurrentView();
    }

    /**
     * Initializes the presentation. This method allows the presentation to be
     * initialized but not displayed. Typically this would be done in a separate
     * thread to reduce the intialization time.
     */
    @Override
    public void initPresentation()
    {
        mw.createHelpWindow();
    }

    /**
     * Displays the presentation to the user.
     */
    @Override
    public void setDisplayed(boolean b)
    {
        debug("displayHelp: " + b);
        // mw.setDisplayed(b);
    }

    /**
     * Determines if the presentation is displayed.
     */
    @Override
    public boolean isDisplayed()
    {
        return mw.isDisplayed();
    }

    /**
     * Requests the presentation be located at a given position.
     */
    @Override
    public void setLocation(Point p)
    {
        mw.setLocation(p);
    }

    /**
     * Requests the location of the presentation.
     * 
     * @returns Point the location of the presentation.
     */
    @Override
    public Point getLocation()
    {
        return mw.getLocation();
    }

    /**
     * Requests the presentation be set to a given size.
     */
    @Override
    public void setSize(Dimension d)
    {
        mw.setSize(d);
    }

    /**
     * Requests the size of the presentation.
     * 
     * @returns Point the location of the presentation.
     */
    @Override
    public Dimension getSize() throws javax.help.UnsupportedOperationException
    {
        return mw.getSize();
    }

    /**
     * Hides/Shows view.
     */
    @Override
    public void setViewDisplayed(boolean displayed)
    {
        mw.setViewDisplayed(displayed);
    }

    /**
     * Determines if the current view is visible.
     */
    @Override
    public boolean isViewDisplayed()
    {
        return mw.isViewDisplayed();
    }

    /**
     * Shows this ID.
     * 
     * @param id
     *            A string that identifies the topic to show for the loaded
     *            (top) HelpSet
     * @param presentation
     *            The Presentation class to display the Help in.
     * @param presentationName
     *            The name of a Presentation section from a HelpSet to use. For
     *            some Presentations this will also be the name to apply to the
     *            Presentation.
     * @exception BadIDException
     *                The ID is not valid for the HelpSet
     * @exception IllegalArgumentException
     *                if presentation is not valid
     * @see Presentation
     */
    @Override
    public void showID(String id, String presentation, String presentationName) throws BadIDException
    {
        debug("showID - string");
        Presentation pres = getPresentation(presentation, presentationName);
        if (pres != null)
        {
            pres.setCurrentID(id);
            pres.setDisplayed(true);
        }
    }

    /**
     * Show this ID.
     * 
     * @param id
     *            a Map.ID indicating the URL to display
     * @param presentation
     *            The Presentation class to display the Help in.
     * @param presentationName
     *            The name of a Presentation section from a HelpSet to use. For
     *            some Presentations this will also be the name to apply to the
     *            Presentation.
     * @exception InvalidHelpSetContextException
     *                if the current helpset does not contain id.helpset
     * @see Presentation
     */
    @Override
    public void showID(ID id, String presentation, String presentationName) throws InvalidHelpSetContextException
    {
        debug("showID - ID");
        Presentation pres = getPresentation(presentation, presentationName);
        if (pres != null)
        {
            pres.setCurrentID(id);
            pres.setDisplayed(true);
        }
    }

    private Presentation getPresentation(String presentation, String presentationName)
    {
        Presentation pres;

        HelpSet hs = mw.getHelpSet();
        // should never happen, but...
        if (hs == null)
            return null;
        ClassLoader loader;
        Class<?> klass;
        Class<?> types[] = { HelpSet.class, String.class };
        Object args[] = { hs, presentationName };
        try
        {
            loader = hs.getLoader();
            if (loader == null)
            {
                klass = Class.forName(presentation);
            }
            else
            {
                klass = loader.loadClass(presentation);
            }
            Method m = klass.getMethod("getPresentation", types);
            pres = (Presentation) m.invoke(null, args);
        }
        catch (ClassNotFoundException cnfex)
        {
            throw new IllegalArgumentException(presentation + "presentation  invalid");
        }
        catch (Exception ex)
        {
            throw new RuntimeException("error invoking presentation");
        }

        if (pres == null)
            return null;

        // It is not possible to call this method for Popup
        if (pres instanceof javax.help.Popup)
            return null;

        return pres;
    }

    /**
     * Shows this ID as content relative to the (top) HelpSet for the HelpBroker
     * instance--HelpVisitListeners are notified.
     * 
     * @param id
     *            A string that identifies the topic to show for the loaded
     *            (top) HelpSet
     * @exception BadIDException
     *                The ID is not valid for the HelpSet
     */
    @Override
    public void setCurrentID(String id) throws BadIDException
    {
        try
        {
            debug("setCurrentID(string): " + id);
            mw.setCurrentID(id);
        }
        catch (BadIDException ex)
        {
            System.out.println("  BadIDException: " + ex);
        }
    }

    /**
     * Displays this ID--HelpVisitListeners are notified.
     * 
     * @param id
     *            a Map.ID indicating the URL to display
     * @exception InvalidHelpSetContextException
     *                if the current helpset does not contain id.helpset
     */
    @Override
    public void setCurrentID(ID id) throws InvalidHelpSetContextException
    {
        // id.id

        try
        {
            debug("setCurrentID(ID): " + id.id);
            mw.setCurrentID(id);
        }
        catch (InvalidHelpSetContextException ex)
        {
            System.out.println("  InvalidHelpSetContextException: " + ex);
        }

    }

    /**
     * Determines which ID is displayed (if any).
     */
    @Override
    public ID getCurrentID()
    {
        return mw.getCurrentID();
    }

    /**
     * Displays this URL. HelpVisitListeners are notified. The currentID changes
     * if there is a mathing ID for this URL
     * 
     * @param url
     *            The url to display. A null URL is a valid url.
     */
    @Override
    public void setCurrentURL(URL url)
    {
        debug("setCurrentURL");
        mw.setCurrentURL(url);
    }

    /**
     * Determines which URL is displayed.
     */
    @Override
    public URL getCurrentURL()
    {
        return mw.getCurrentURL();
    }

    // Context-Senstive methods
    /**
     * Enables the Help key on a Component. This method works best when the
     * component is the rootPane of a JFrame in Swing implementations, or a
     * java.awt.Window (or subclass thereof) in AWT implementations. This method
     * sets the default helpID and HelpSet for the Component and registers
     * keyboard actions to trap the "Help" keypress. When the "Help" key is
     * pressed, if the object with the current focus has a helpID, the helpID is
     * displayed. otherwise the default helpID is displayed.
     * 
     * @param comp
     *            the Component to enable the keyboard actions on.
     * @param id
     *            the default HelpID to be displayed
     * @param hs
     *            the default HelpSet to be displayed. If hs is null the default
     *            HelpSet will be assumed.
     */
    @Override
    public void enableHelpKey(Component comp, String id, HelpSet hs)
    {
        enableHelpKey(comp, id, hs, null, null);
    }

    /**
     * Enables the Help key on a Component. This method works best when the
     * component is the rootPane of a JFrame in Swing implementations, or a
     * java.awt.Window (or subclass thereof) in AWT implementations. This method
     * sets the default helpID and HelpSet for the Component and registers
     * keyboard actions to trap the "Help" keypress. When the "Help" key is
     * pressed, if the object with the current focus has a helpID, the helpID is
     * displayed. otherwise the default helpID is displayed.
     * 
     * @param comp
     *            the Component to enable the keyboard actions on.
     * @param id
     *            the default HelpID to be displayed
     * @param hs
     *            the default HelpSet to be displayed. If hs is null the default
     *            HelpSet from the HelpBroker is assummed. null is not valid if
     *            presenation is not null.
     * @param presentation
     *            The Presentation class to display the content in. If
     *            presentation is null the HelpBroker is used to display the
     *            content.
     * @param presentationName
     *            The name of a Presentation section to control the display of
     *            the content. Also for some Presenations this will be used to
     *            "name" the Presentation.
     * 
     * @throws IllegalArgumentException
     *             if presentation != null and hs == null
     */
    @Override
    public void enableHelpKey(Component comp, String id, HelpSet hs, String presentation, String presentationName)
    {
        if (id == null)
            throw new NullPointerException("id");
        if (presentation != null && hs == null)
            throw new IllegalArgumentException("hs");
        CSH.setHelpIDString(comp, id);
        if (hs != null)
        {
            CSH.setHelpSet(comp, hs);
        }
        if (comp instanceof JComponent)
        {
            JComponent root = (JComponent) comp;
            ActionListener al = null;
            if (presentation == null)
            {
                al = getDisplayHelpFromFocus();
            }
            else
            {
                al = new CSH.DisplayHelpFromFocus(hs, presentation, presentationName);
            }
            root.registerKeyboardAction(al, KeyStroke.getKeyStroke(KeyEvent.VK_HELP, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            root.registerKeyboardAction(al, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        }
        else
        {
            helpKeyHS = hs;
            helpKeyPresentation = presentation;
            helpKeyPresentationName = presentationName;
            comp.addKeyListener(this);
        }
    }

    /**
     * Invoked when a key is typed. This event occurs when a key press is
     * followed by a key release. Not intended to be overridden or extended.
     */
    @Override
    public void keyTyped(KeyEvent e)
    {
        // ignore
    }

    /**
     * Invoked when a key is pressed. Not intended to be overridden or extended.
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        // ingore
    }

    /**
     * Invoked when a key is released. Not intended to be overridden or
     * extended.
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        // simulate what is done in JComponents registerKeyboardActions.
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_F1 || code == KeyEvent.VK_HELP)
        {
            ActionListener al = null;
            if (helpKeyHS != null)
            {
                al = new CSH.DisplayHelpFromFocus(helpKeyHS, helpKeyPresentation, helpKeyPresentationName);
            }
            else
            {
                al = getDisplayHelpFromFocus();
            }
            al.actionPerformed(new ActionEvent(e.getComponent(), ActionEvent.ACTION_PERFORMED, null));
        }

    }

    /**
     * Enables help for a Component. This method sets a component's helpID and
     * HelpSet.
     * 
     * @param comp
     *            the Component to set the id and hs on.
     * @param id
     *            the String value of an Map.ID.
     * @param hs
     *            the HelpSet the id is in. If hs is null the default HelpSet
     *            will be assumed.
     */
    @Override
    public void enableHelp(Component comp, String id, HelpSet hs)
    {
        if (id == null)
            throw new NullPointerException("id");
        CSH.setHelpIDString(comp, id);
        if (hs != null)
        {
            CSH.setHelpSet(comp, hs);
        }
    }

    /**
     * Enables help for a MenuItem. This method sets a component's helpID and
     * HelpSet.
     * 
     * @param comp
     *            the MenuItem to set the id and hs on.
     * @param id
     *            the String value of an Map.ID.
     * @param hs
     *            the HelpSet the id is in. If hs is null the default HelpSet
     *            will be assumed.
     */
    @Override
    public void enableHelp(MenuItem comp, String id, HelpSet hs)
    {
        if (id == null)
            throw new NullPointerException("id");
        CSH.setHelpIDString(comp, id);
        if (hs != null)
        {
            CSH.setHelpSet(comp, hs);
        }
    }

    /**
     * Enables help for a Component. This method sets a Component's helpID and
     * HelpSet and adds an ActionListener. When an action is performed it
     * displays the Component's helpID and HelpSet in the default viewer.
     * 
     * @param comp
     *            the Component to set the id and hs on. If the Component is not
     *            a javax.swing.AbstractButton or a java.awt.Button an
     *            IllegalArgumentException is thrown.
     * @param id
     *            the String value of an Map.ID.
     * @param hs
     *            the HelpSet the id is in. If hs is null the default HelpSet
     *            will be assumed.
     * 
     * @see javax.swing.AbstractButton
     * @see java.awt.Button
     * @throws IllegalArgumentException
     *             if comp is null.
     */
    @Override
    public void enableHelpOnButton(Component comp, String id, HelpSet hs)
    {
        if (!(comp instanceof AbstractButton) && !(comp instanceof Button))
            throw new IllegalArgumentException("Invalid Component");
        if (id == null)
            throw new NullPointerException("id");
        CSH.setHelpIDString(comp, id);
        if (hs != null)
        {
            CSH.setHelpSet(comp, hs);
        }
        if (comp instanceof AbstractButton)
        {
            AbstractButton button = (AbstractButton) comp;
            button.addActionListener(getDisplayHelpFromSource());
        }
        else if (comp instanceof Button)
        {
            Button button = (Button) comp;
            button.addActionListener(getDisplayHelpFromSource());
        }
    }

    /**
     * Enables help for a MenuItem. This method sets a Component's helpID and
     * HelpSet and adds an ActionListener. When an action is performed it
     * displays the Component's helpID and HelpSet in the default viewer.
     * 
     * @param comp
     *            the MenuItem to set the id and hs on. If comp is null an
     *            IllegalAgrumentException is thrown.
     * @param id
     *            the String value of an Map.ID.
     * @param hs
     *            the HelpSet the id is in. If hs is null the default HelpSet
     *            will be assumed.
     * @see java.awt.MenuItem
     * @throws IllegalArgumentException
     *             if comp is null.
     */
    @Override
    public void enableHelpOnButton(MenuItem comp, String id, HelpSet hs)
    {
        if (comp == null)
            throw new IllegalArgumentException("Invalid Component");
        if (id == null)
            throw new NullPointerException("id");
        CSH.setHelpIDString(comp, id);
        if (hs != null)
        {
            CSH.setHelpSet(comp, hs);
        }
        comp.addActionListener(getDisplayHelpFromSource());
    }

    /**
     * Enables help for a Component. This method sets a Component's helpID and
     * HelpSet and adds an ActionListener. When an action is performed it
     * displays the Component's helpID and HelpSet in the default viewer.
     * 
     * @param obj
     *            the Component to set the id and hs on. If the Component is not
     *            a javax.swing.AbstractButton or a java.awt.Button an
     *            IllegalArgumentException is thrown.
     * @param id
     *            the String value of an Map.ID.
     * @param hs
     *            the HelpSet the id is in. If hs is null the default HelpSet
     *            will be assumed.
     * 
     * @see javax.swing.AbstractButton
     * @see java.awt.Button
     * @throws IllegalArgumentException
     *             if comp is null.
     */
    @Override
    public void enableHelpOnButton(Object obj, String id, HelpSet hs, String presentation, String presentationName)
    {
        if (!(obj instanceof AbstractButton) && !(obj instanceof Button) && !(obj instanceof MenuItem))
            throw new IllegalArgumentException("Invalid Object");
        if (id == null)
            throw new NullPointerException("id");

        if (obj instanceof AbstractButton || obj instanceof Button)
        {
            CSH.setHelpIDString((Component) obj, id);
            if (hs != null)
            {
                CSH.setHelpSet((Component) obj, hs);
            }
        }
        else
        {
            CSH.setHelpIDString((MenuItem) obj, id);
            if (hs != null)
            {
                CSH.setHelpSet((MenuItem) obj, hs);
            }
        }

        if (presentation == null)
        {
            if (obj instanceof AbstractButton)
            {
                AbstractButton button = (AbstractButton) obj;
                button.addActionListener(getDisplayHelpFromSource());
            }
            else if (obj instanceof Button)
            {
                Button button = (Button) obj;
                button.addActionListener(getDisplayHelpFromSource());
            }
            else if (obj instanceof MenuItem)
            {
                MenuItem item = (MenuItem) obj;
                item.addActionListener(getDisplayHelpFromSource());
            }
        }
        else
        {
            if (obj instanceof AbstractButton)
            {
                AbstractButton button = (AbstractButton) obj;
                button.addActionListener(new CSH.DisplayHelpFromSource(hs, presentation, presentationName));
            }
            else if (obj instanceof Button)
            {
                Button button = (Button) obj;
                button.addActionListener(new CSH.DisplayHelpFromSource(hs, presentation, presentationName));
            }
            else if (obj instanceof MenuItem)
            {
                MenuItem item = (MenuItem) obj;
                item.addActionListener(new CSH.DisplayHelpFromSource(hs, presentation, presentationName));
            }
        }

    }

    /**
     * Returns the default DisplayHelpFromFocus listener.
     */
    @Override
    protected ActionListener getDisplayHelpFromFocus()
    {
        if (displayHelpFromFocus == null)
        {
            displayHelpFromFocus = new CSH.DisplayHelpFromFocus(this);
        }
        return displayHelpFromFocus;
    }

    /**
     * Returns the default DisplayHelpFromSource listener.
     */
    @Override
    protected ActionListener getDisplayHelpFromSource()
    {
        if (displayHelpFromSource == null)
        {
            displayHelpFromSource = new CSH.DisplayHelpFromSource(this);
        }
        return displayHelpFromSource;
    }

    /**
     * Set the activation window from given Component or MenuItem. It find
     * Window component in the component tree from given Component or MenuItem
     * end call
     * 
     * <pre>
     * setActivationWindow
     * </pre>
     * 
     * @parem comp the activation Component or MenuItem
     * @since 1.1
     * 
     */
    @Override
    public void setActivationObject(Object comp)
    {
        mw.setActivationObject(comp);
    }

    /**
     * Set the activation window. If the window is an instance of a Dialog and
     * the is modal, modallyActivated help is set to true and ownerDialog is set
     * to the window. In all other instances modallyActivated is set to false
     * and ownerDialog is set to null.
     * 
     * @param window
     *            the activating window
     * @since 1.1
     */
    @Override
    public void setActivationWindow(Window window)
    {
        mw.setActivationWindow(window);
    }

    // the listeners.
    protected ActionListener displayHelpFromFocus;
    protected ActionListener displayHelpFromSource;

    /*
     * Make sure the Look and Feel will be set
     */
    // static
    // {
    // SwingHelpUtilities.installLookAndFeelDefaults();
    // }
    /**
     * Debugging code...
     */

    private static final boolean debug = true;

    private static void debug(Object msg)
    {
        if (debug)
        {
            System.err.println("DummyHelpBroker: " + msg);
        }
    }

}
