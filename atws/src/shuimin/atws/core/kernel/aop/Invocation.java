package shuimin.atws.core.kernel.aop;

import java.lang.reflect.InvocationTargetException;

import com.shuimin.base.A;
import com.shuimin.base.abstraction.Namable;

public final class Invocation implements Namable<Invocation>
{
	private final Object realObject;
	private String methodName;
	
	public Invocation(Object obj, String methodName)
	{
		A._assert(obj, "obj null");
		this.realObject = obj;
		this.methodName = methodName;
	}

	public Object invoke(Object... args) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
		IllegalAccessException, InvocationTargetException
	{
		Class<?>[] parameterClasses = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			parameterClasses[i] = args[i].getClass();
		}
		return realObject.getClass().getMethod(methodName, parameterClasses).invoke(realObject, args);
	}

	@Override
	public String name()
	{
		return methodName;
	}

	@Override
	public Invocation name(String name)
	{
		methodName = name;
		return this;
	}
}
