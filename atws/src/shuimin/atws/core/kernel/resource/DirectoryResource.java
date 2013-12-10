package shuimin.atws.core.kernel.resource;

import java.util.Collection;

import shuimin.atws.util.StrUtils;

import com.shuimin.base.A;
import com.shuimin.base.struc.tree.TreeNode;

/**
 * 
 * @author ed
 */
public class DirectoryResource extends AbstractResource
{

	protected DirectoryResource()
	{
	}

	/**
	 * here means access resource under this
	 */
	private Resource _access(String name) throws ResourceNotFoundException
	{
		A._assert(node, " node null");
		for (TreeNode<Resource> nr : node) {
			Resource r = nr.value();
			if (StrUtils.notBlank(r.name())
				&& r.name().equals(name)) {
				return r;
			}
		}
		throw new ResourceNotFoundException(this);
	}

	@Override
	public Resource exec(Object x) throws Exception
	{
		if (x instanceof String) {
			return _access((String) x);
		}
		throw new RuntimeException("error when trying enter " + x
			+ " under " + this);
	}

	@Override
	public Void write(Object w) throws Exception
	{
		throw new RuntimeException("you do not modify this Directory");
	}

	/**
	 * list
	 */
	@Override
	public Collection<Resource> read(Object r) throws Exception
	{
		return node.childrenValues();
	}

	public void addChild(Resource r)
	{
		node.append(r.name(), r);
	}

	@Override
	public Resource select(String name)
	{
		try {
			return (Resource) _access(name);
		}
		catch (ResourceNotFoundException e) {
			return null;
		}
	}
}