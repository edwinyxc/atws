package test.dummy.testmod1.bo;

import com.shuimin.base.X;

import shuimin.atws.core.kernel.resource.RegularResource;

public class BO extends RegularResource
{
	@Override
	public Object read(Object r) throws Exception
	{
		X.echo("bobobo\n");
		return null;
	}
}
