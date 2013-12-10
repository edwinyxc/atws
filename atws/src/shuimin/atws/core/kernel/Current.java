package shuimin.atws.core.kernel;

import java.util.Map;
import java.util.TreeMap;

import shuimin.atws.core.kernel.resource.ResourceTreeNode;

/**
 * @author Edwin
 */
public class Current
{
	/*
	 * a Call Object is the only way to active with kernel
	 */
	private static final ThreadLocal<Map<String, Object>> attrs = 
		new ThreadLocal<Map<String, Object>>();

	public static void attr(String key, Object val)
	{
		_createIfEmpty(attrs.get()).put(key, val);
	}

	public static Object attr(Object key)
	{
		return _createIfEmpty(attrs.get()).get(key);
	}

	public static ResourceTreeNode curNode()
	{
		ResourceTreeNode node = (ResourceTreeNode) attr("cur_node");
		if (node == null) {
			node = (ResourceTreeNode) AppContext.instance().rt()
				.root();
		}
		return node;
	}

	public static void curNode(ResourceTreeNode node)
	{
		attr("cur_node", node);
	}

	private static Map<String, Object> _createIfEmpty(Map<String, Object> c)
	{
		if (c != null)
			return c;

		c = new TreeMap<String, Object>();
		attrs.set(c);
		return c;
	}
}
