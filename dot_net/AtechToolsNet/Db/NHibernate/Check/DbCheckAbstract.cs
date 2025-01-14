

/**
 *  This file is part of ATech Tools library.
 *  
 *  <one line to give the library's name and a brief idea of what it does.>
 *  Copyright (C) 2009  Andy (Aleksander) Rozman (Atech-Software)
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

using System;
using System.Reflection;
using System.Collections.Generic;
using NHibernate.Cfg;
namespace ATechTools.Db.NHibernate.Check
{

    public abstract class DbCheckAbstract : DbCheckInterface
    {

        public Dictionary<string, Assembly> ResourceAssemblies = new Dictionary<string, Assembly>();


        public DbCheckAbstract()
        { 
            this.ResourceAssemblies.Add("DbInfo.hbm.xml", this.GetType().Assembly);
            this.LoadUserResourceAssemblyInformation();
        }


        public abstract void LoadUserResourceAssemblyInformation();


        /**
         * Is Check Enabled
         * 
         * @see com.atech.db.hibernate.check.DbCheckInterface#isCheckEnabled()
         */
        public abstract bool IsCheckEnabled();

        /**
         * Get DbInfo Resource
         * 
         * @see com.atech.db.hibernate.check.DbCheckInterface#getDbInfoResource()
         */
        public string[] GetDbInfoResource()
        {
            return (new string[] { "DbInfo.hbm.xml" });
        }

        /**
         * Get DbInfo Configuration
         * 
         * @see com.atech.db.hibernate.check.DbCheckInterface#getDbInfoConfiguration()
         */
        public abstract Configuration GetDbInfoConfiguration();



        public virtual Assembly GetResourceAssembly(string resName)
        { 
            if (this.ResourceAssemblies.ContainsKey(resName))
            {
                Console.WriteLine("Get Resource Assembly found: " + this.ResourceAssemblies[resName]);
                return this.ResourceAssemblies[resName];
            }
            else
            {
                Console.WriteLine("Get Resource Assembly: " + this.GetType().Assembly);
                return this.GetType().Assembly;
            }
        }


        public abstract string DbName { get; }


        //public String DbInfoReportFilename { get; }

        public abstract String DbInfoReportFilename { get; }



    }
}