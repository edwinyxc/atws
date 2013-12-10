package shuimin.atws.core.kernel.module;

import java.util.Map;

import shuimin.atws.core.kernel.resource.Resource;
import shuimin.atws.core.kernel.resource.ResourceTreeNode;

/**
 * 模块
 * 
 * @author ed
 * 
 */
public interface Module extends Resource
{

	public String getMid();

	// public String[] getDependencies();

	public ResourceTreeNode node();

	// public Module modInApp(String mid);

	/**
	 * get attribute of a specific module
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map attrs();

	public Object attr(String key);

	public void attr(String key, Object val);

	// public Module dependentModule(String mid);

	public void init();
}
