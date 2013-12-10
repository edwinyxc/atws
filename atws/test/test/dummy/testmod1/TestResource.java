package test.dummy.testmod1;

import shuimin.atws.core.kernel.resource.RegularResource;

public class TestResource extends RegularResource
{
	@Override
	public Object read(Object r) throws Exception
	{
		System.out.println("!!!!!#@#@#@#!@#@#@####@@@@SUCCESS@#@#!");
		return super.read(r);
	}
}
