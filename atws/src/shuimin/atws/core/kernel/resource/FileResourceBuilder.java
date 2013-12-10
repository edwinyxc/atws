package shuimin.atws.core.kernel.resource;

import java.io.File;

import com.shuimin.base.A;

public class FileResourceBuilder extends AbstractResourceBuilder{

	@Override
	public Resource build(Object[] args)
	{
		A._assert(args!=null 
			&& args.length>=2
			&& args[0] instanceof Resource
			&& args[1] instanceof File,
			"arguments not right :args!=null "
			+ "&& args.length>=2 "
			+ "&& args[0] instanceof Resource "
			+ "&& args[1] instanceof File");
		
		Resource parent = (Resource) args[0];
		File file = (File) args[1];
		ResourceTreeNode parent_node = parent.node();
		A._assert(parent_node, "parent node null");
		A._assert(file, "file null");
		RegularResource r = new FileResource(file);
		ResourceTreeNode node = new ResourceTreeNode(r.name(), r);
		node.appendTo(parent_node);
		return r;
	}
}
