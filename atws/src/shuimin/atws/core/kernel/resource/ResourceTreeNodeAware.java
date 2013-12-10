package shuimin.atws.core.kernel.resource;

public interface ResourceTreeNodeAware
{
	/**
         * the related ResourceTreeNode
         * @return
         */
        public ResourceTreeNode node();
        
        public void node(ResourceTreeNode node);
}
