package shuimin.atws.core.kernel.resource;

import com.shuimin.base.abstraction.Namable;

public interface ResourceBuilder extends Namable<ResourceBuilder>
{
	/**
	 * 
	 * @param clazz
	 * @param args
	 * @return
	 */
	public Resource build(Object[] args) throws Exception;
}
