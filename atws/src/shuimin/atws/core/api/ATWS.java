package shuimin.atws.core.api;

import java.util.Map;
import java.util.Properties;

import shuimin.atws.core.kernel.AppContext;
import shuimin.atws.core.kernel.Modules;
import shuimin.atws.core.kernel.Resources;
import shuimin.atws.core.kernel.module.Module;
import shuimin.atws.core.kernel.module.ModuleNotFoundException;
import shuimin.atws.core.kernel.resource.Resource;
import shuimin.atws.core.kernel.resource.ResourceNotFoundException;

import com.shuimin.base.logger.Logger;

/**
 * System access class DO NOT USE THESE METHODS IN THE ATWS PKG!!! MAY CAUSE
 * PROBLEM
 * 
 * @author Edwin
 */
public class ATWS
{
	/*
	 * system api
	 */

	/**
	 * get the system context
	 * 
	 * @return
	 */
	public static AppContext ctx()
	{
		return AppContext.instance();
	}

	/**
	 * system logger
	 * 
	 * @return
	 */
	public static Logger sysLogger()
	{
		return ctx().logger;
	}

	public static Properties sysSettings()
	{
		return ctx().settings;
	}

	@SuppressWarnings("rawtypes")
	public static Map sysAttributes()
	{
		return ctx().Attributes();
	}

//	public static Module module(String mid) throws ResourceNotFoundException
//	{
//		return (Module) open(mid);
//	}

	/*
	 * system calls
	 */
	/**
	 * check the resource
	 * 
	 * @param selector
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public static Resource open(String selector) throws ResourceNotFoundException
	{
		sysLogger().info("OPEN R : " + selector);
		return Resources.get(selector);
	}
	
	public static Object read(String selector)
	{
		return read(selector,null);
	}
	
	public static Object exec(String selector)
	{
		return exec(selector,null);
	}
	public static Object read(String selector,Object params)
	{
		try {
			open(selector).read(params);
		}
		catch (ResourceNotFoundException e) {
			sysLogger().err("R : " + selector+" NOT FOUND");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object write(String selector, Object params)
	{
		try {
			open(selector).write(params);
		}
		catch (ResourceNotFoundException e) {
			sysLogger().err("R : " + selector+" NOT FOUND");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object exec(String selector, Object params)
	{
		try {
			open(selector).exec(params);
		}
		catch (ResourceNotFoundException e) {
			sysLogger().err("R : " + selector+" NOT FOUND");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static class module {
		public static Module find(String mid){
			try {
				return Modules.find(mid);
			}
			catch (ModuleNotFoundException e) {
				sysLogger().err("module "+mid+" not found");
				throw new RuntimeException(e);
			}
		}
	}
	

	// TODO not finished new functions here
}
