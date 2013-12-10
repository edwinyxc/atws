package shuimin.atws.core.kernel;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import shuimin.atws.core.kernel.resource.ResourceTree;

/**
 * 
 * @author Edwin
 */
class Global
{
	/**
	 * global
	 */
	final static protected Map<String, Object> attributes = new TreeMap<String, Object>();

	protected static final Properties settings = new Properties();
	/**
	 * resource systems holder;
	 */

	protected static ResourceTree RT;

	protected static void init()
	{
		RT = ResourceTree.instance();
	}
}
