package shuimin.atws.core.kernel.resource;

import shuimin.atws.core.kernel.AppContext;

import com.shuimin.base.A;

public class DirectoryResourceBuilder extends AbstractResourceBuilder
{
	@Override
	public Resource build(Object[] args) throws Exception
	{
		A._assert(args != null && args.length >= 2
			&& args[0] instanceof Resource
			&& args[1] instanceof Class<?>,
			"arguments not right :args!=null "
				+ "&& args.length>=2 "
				+ "&& args[0] instanceof Resource "
				+ "&& args[1] instanceof Class<?>");
		Resource parent = (Resource) args[0];
		Class<?> r_class = (Class<?>) args[1];
		ResourceTreeNode parent_node = parent.node();
		A._assert(parent_node, "parent node null");
		DirectoryResource d = (DirectoryResource) r_class.newInstance();
		ResourceTreeNode node = new ResourceTreeNode(d.name(), d);
		AppContext.instance().logger.debug(node.name()
			+ " appending to " + parent_node.name());
		node.appendTo(parent_node);
		return d;
	}
}
