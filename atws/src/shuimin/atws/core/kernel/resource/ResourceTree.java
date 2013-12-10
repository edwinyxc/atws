package shuimin.atws.core.kernel.resource;

import com.shuimin.base.struc.tree.Tree;

public class ResourceTree extends Tree<Resource>
{
	private static final ResourceTree INSTANCE = HOLDER.holder;

	private ResourceTree()
	{
		DirectoryResource root_d = (DirectoryResource) new DefaultModule();
		root_d.name = "/";
		ResourceTreeNode node = new ResourceTreeNode(root_d.name(),
			root_d);
		setRoot(node);
	}

	private final static class HOLDER
	{
		private final static ResourceTree holder = new ResourceTree();
	}

	public static ResourceTree instance()
	{
		return INSTANCE;
	}
}
