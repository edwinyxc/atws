package shuimin.atws.core.kernel.resource;

import com.shuimin.base.A;

/**
 * 
 * @author Edwin
 */
public abstract class AbstractResource implements Resource
{

	protected String name = java.beans.Introspector.decapitalize(this
		.getClass().getSimpleName());

	protected ResourceTreeNode node;

	@Override
	public ResourceTreeNode node()
	{
		return node;
	}

	@Override
	public void node(ResourceTreeNode node)
	{
		this.node = node;
	}

	@Override
	public String[] path()
	{
		A._assert(node, "node null");
		return node.path();
	}

	@Override
	public String name()
	{
		return name;
	}

	@Override
	public final Resource name(String name)
	{
		if(node != null)
			this.node.name(name);
		this.name = name;
		return this;
	}

	@Override
	public String toString()
	{
		return "{name:" + name +"}";
	}
}
