package shuimin.atws.core.kernel.resource;

import com.shuimin.base.A;

abstract class AbstractResourceBuilder implements ResourceBuilder
{
	@Override
	public String name()
	{
		return java.beans.Introspector
			.decapitalize(this.getClass().getSimpleName());
	}

	@Override
	public AbstractResourceBuilder name(String name)
	{
		A._assert(false, "dont rename me , my name eq class name");
		return null;
	}
	
}
