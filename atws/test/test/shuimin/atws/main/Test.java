package test.shuimin.atws.main;

import java.util.Properties;

import shuimin.atws.Const;
import shuimin.atws.core.api.ATWS;
import shuimin.atws.core.kernel.AppContext;

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
		
		ATWS.read("/testmod1/testResource",null);
		ATWS.read("testmod2/testResource",null);
		ATWS.read("testmod1/bo/BO",null);
		final String name = (String) ATWS.module.find("testmod1").attr("name");
		System.out.println("name="+name);
	}

}
