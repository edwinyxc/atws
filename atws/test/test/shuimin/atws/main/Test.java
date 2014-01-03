package test.shuimin.atws.main;

import java.util.Properties;

import shuimin.atws.Const;
import shuimin.atws.core.api.ATWS;
import shuimin.atws.core.kernel.AppContext;
import shuimin.atws.core.kernel.module.Module;
import shuimin.atws.sys.auth.AuthAPI;

public class Test
{
	public static void main(String[] args)
	{
		/**
		 * project_name=hrm
		 * debug_level=debug
		 */
		Properties prop = new Properties();
		prop.setProperty("project_name", "test");
		prop.setProperty(Const.MODULE.PROP_KEY_PKG_NAME, "test.dummy");
		prop.setProperty("debug_level", "debug");
		AppContext.instance().init(prop);
		
		System.out.println(AppContext.instance().rt().debug());
		
		final String name = (String) ATWS.module.find("auth").attr("name");
		Module a =  ATWS.module.find("auth").api();
		
		final String description = (String) ATWS.module.find("auth").attr("description");
		System.out.println("name="+name);
		System.out.println("description="+description);
	}
}

