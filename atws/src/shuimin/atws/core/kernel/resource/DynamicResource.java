package shuimin.atws.core.kernel.resource;

import java.util.Collection;

import com.shuimin.base.A;

/**
 * DynaResource[QO,BO,MODEL。。。] anything not singleton and flexible
 * they are contained in some special containers 
 * most frequently LRU(LAST RECENTLY USED)-cache 
 * @author Edwin
 *
 */
public abstract class DynamicResource extends AbstractResource
{
	private Collection<Resource> container;
	
	/**
	 * remove self from resourceTree
	 */
	public void removeSelf()
	{
		// remove this
		if (container != null) {
			container.remove(this);
		}
		// remove value
		if (this.node != null) {
			if (this.node.parent() != null) {
				this.node.parent().children().remove(this.node);
			}
		}
	}
	
	/**
	 * do sync 
	 * may be access from web
	 * or  access dao to get a bo
	 */
	abstract public void _sync();
	
	/**
	 * sync symbol
	 * @return
	 */
	abstract protected boolean _needSync();

	@Override
	public DynamicResource read(Object r) throws Exception
	{	
		if(_needSync()) synchronized(this){_sync();}
		return this;
	}
	
	@Override
	public Void write(Object w) throws Exception
	{
		A._assert(false, "dont modify a dynamic resource");
		return null;
	}
}
