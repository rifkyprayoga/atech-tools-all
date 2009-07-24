package com.atech.i18n.tool.client.db.hibernate;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

// TODO: Auto-generated Javadoc
/* * @author Hibernate CodeGenerator */
/**
 * The Class ApplicationH.
 */
public class ApplicationH implements Serializable
{

    private static final long serialVersionUID = 3243905046109418673L;

    /** identifier field */
    private long id;

    /** persistent field */
    private String name;

    /** nullable persistent field */
    private String desc;

    /** 
     * full constructor 
     * 
     * @param name 
     * @param desc 
     */
    public ApplicationH(String name, String desc)
    {
        this.name = name;
        this.desc = desc;
    }

    /** 
     * default constructor 
     */
    public ApplicationH()
    {
    }

    /** minimal constructor 
     * @param name */
    public ApplicationH(String name)
    {
        this.name = name;
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public long getId()
    {
        return this.id;
    }

    /**
     * Sets the id.
     * 
     * @param id the new id
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Sets the name.
     * 
     * @param name the new name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the desc.
     * 
     * @return the desc
     */
    public String getDesc()
    {
        return this.desc;
    }

    /**
     * Sets the desc.
     * 
     * @param desc the new desc
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /** 
     * toString
     */
    public String toString()
    {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    /** 
     * equals
     */
    public boolean equals(Object other)
    {
        if (!(other instanceof ApplicationH))
            return false;
        ApplicationH castOther = (ApplicationH) other;
        return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
    }

    /** 
     * hashCode
     */
    public int hashCode()
    {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

}