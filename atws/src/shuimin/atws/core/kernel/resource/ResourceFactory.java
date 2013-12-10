package shuimin.atws.core.kernel.resource;

import java.util.Map;
import java.util.TreeMap;

import shuimin.atws.core.kernel.AppContext;

import com.shuimin.base.A;

/**
 * THE Resource Factory!!! create all resource
 * for concrete resource building
 * 
 * @author ed
 * 
 */
public class ResourceFactory
{

	private final static Map<String, ResourceBuilder> builders = new TreeMap<String, ResourceBuilder>();
	
	public static final void init(){
		/*set the basic builders*/
		setBuilder(new DirectoryResourceBuilder());
		setBuilder(new RegularResourceBuilder());
		setBuilder(new FileResourceBuilder());
	}
	
	public static void setBuilder(ResourceBuilder b)
	{
		String b_name = b.name();
		A._assert(b_name, "b_name null");
		if (builders.get(b_name) != null) {
			AppContext.instance().logger
				.info("Resource Builder: " + b_name
					+ " already exists");
			return;
		}
		builders.put(b.name(), b);
	}

	public static Resource build(String name, Object[] args) throws Exception
	{
		ResourceBuilder b = builders.get(name);
		if (b == null) {
			AppContext.instance().logger
				.info("Resource Builder: " + name
					+ " not exists");
			return null;
		}
		return b.build(args);
	}

}
