package shuimin.atws.core.kernel;

import java.util.Map;

import shuimin.atws.core.kernel.resource.Resource;
import shuimin.atws.core.kernel.resource.ResourceNotFoundException;
import shuimin.atws.core.kernel.resource.ResourceTree;

import com.shuimin.base.A;
import com.shuimin.base.struc.LruCache;
import com.shuimin.base.struc.tree.TreeNode;
import com.shuimin.base.util.StrUtils;

/**
 * 
 * @author Edwin
 */
public class Resources
{
	private final static Map<String, Resource> lruc = new LruCache<String, Resource>(
		1024);

	private static ResourceTree rt = AppContext.instance().rt();

	public static Resource get(String selector)
		throws ResourceNotFoundException
	{
		if (selector == null)
			return null;
		String abs_selector = selector;
		if (!selector.startsWith("/"))
			abs_selector = _build_abs_selector(selector);
		AppContext.instance().logger.debug("ABS_SELTR : "
			+ abs_selector);
		Resource r = lruc.get(abs_selector);
		if (r != null)
			return r;

		return _slow_get(abs_selector);
	}

	private static String _build_abs_selector(String selector)
	{
		String[] path = Current.curNode().path();
		A._assert(path!=null && path.length>0, "illegal Current path");
		String ret = "";
		for (String p : path) {
			if(StrUtils.isBlank(p))continue;
			if(p.endsWith("/"))ret += p;
			else ret += p + "/";
		}
		ret += selector;
		return ret;
	}

	private static Resource _slow_get(String selector)
		throws ResourceNotFoundException
	{

		TreeNode<Resource> node = rt.select(selector);

		if (node == null)
			throw new ResourceNotFoundException(selector);

		Resource r = node.value();

		/* no need check existence in lru, it`s not there, just put into */
		synchronized (lruc) {
			lruc.put(selector, r);
		}

		return r;
	}
}
