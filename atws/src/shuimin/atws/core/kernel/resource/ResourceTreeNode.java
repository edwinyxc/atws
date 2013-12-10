package shuimin.atws.core.kernel.resource;

import com.shuimin.base.A;
import com.shuimin.base.struc.tree.Selector;
import com.shuimin.base.struc.tree.TreeNode;
import com.shuimin.base.util.StrUtils;

public class ResourceTreeNode extends TreeNode<Resource>
{
	private Selector<TreeNode<Resource>> vselector = new Selector<TreeNode<Resource>>() {
		@Override
		public TreeNode<Resource> select(String name)
		{ 
			for (TreeNode<Resource> node : ResourceTreeNode.this
				.children()) {
				if (node == null)
					continue;
				if (StrUtils.notBlank(node.name())
					&& node.name().equals(name)) {
					return node;
				}
			}
			//not found, hand this selecting procedure to resource
			Resource val = ResourceTreeNode.this.value();
			A._assert(val, "val null");
			Resource r = val.select(name);
			if(r == null)
				return null;
			else{
				ResourceTreeNode v_node = new ResourceTreeNode(name,r);
				//v_node.appendTo(ResourceTreeNode.this);
				//TODO if any possible to cache v_node ??
				return v_node;
			}
		}
	};

	protected ResourceTreeNode(String name, Resource val)
	{
		super(name, val);
		this.selector = vselector;
		val.node(this);
	}

}
