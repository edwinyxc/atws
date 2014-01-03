package shuimin.atws.core.kernel.resource;

import java.util.Map;
import java.util.TreeMap;

import shuimin.atws.core.kernel.AppContext;
import shuimin.atws.core.kernel.Resources;
import shuimin.atws.core.kernel.aop.Invocation;
import shuimin.atws.core.kernel.module.Module;
import shuimin.atws.core.kernel.resource.DirectoryResource;
import shuimin.atws.core.kernel.resource.ResourceTreeNode;

import com.shuimin.base.logger.Logger;

public final class DefaultModule extends DirectoryResource implements Module
{
	protected Logger logger;
	@SuppressWarnings("rawtypes")
	protected final Map attributes = new TreeMap();
	protected String mountPoint;
	protected ResourceTreeNode mountNode;// ?? not for sure
	
	private Invocation[] invos =new  Invocation[0];
	
	protected DefaultModule()
	{
	}

	@Override
	public void init()
	{
		logger = AppContext.instance().logger;
		mountPoint = "/";
		try {
			mountNode = (ResourceTreeNode) Resources.get(mountPoint);
		}
		catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getMid()
	{
		return this.name;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map attrs()
	{
		return attributes;
	}

	@Override
	public Object attr(String key)
	{
		return attributes.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void attr(String key, Object val)
	{
		attributes.put(key, val);
	}

	@Override
	public Invocation[] api()
	{
		return invos;
	}

	@Override
	public Invocation api(String name)
	{
		for(Invocation inv : invos){
			if(inv.name().equals(name)){
				return inv;
			}
		}
		return null;
	}

}
