package com.atech.web.db.objects;

/**
 *  This file is part of ATech Tools library.
 *  
 *  <one line to give the library's name and a brief idea of what it does.>
 *  Copyright (C) 2007  Andy (Aleksander) Rozman (Atech-Software)
 *  
 *  
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA 
 *  
 *  
 *  For additional information about this project please visit our project site on 
 *  http://atech-tools.sourceforge.net/ or contact us via this emails: 
 *  andyrozman@users.sourceforge.net or andy@atech-software.com
 *  
 *  @author Andy
 *
*/


public class Setting  
{


    private long id;

    private String property;

    private String value;

    /** full constructor */
    public Setting(String property, String value) 
    {
        this.property = property;
        this.value = value;
    }

    /** default constructor */
    public Setting() 
    {
    }

    public long getId() 
    {
        return this.id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public String getProperty() 
    {
        return this.property;
    }

    public void setProperty(String property) 
    {
        this.property = property;
    }

    public String getValue() 
    {
        return this.value;
    }

    public void setValue(String value) 
    {
        this.value = value;
    }


}