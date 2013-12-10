package shuimin.atws.core.kernel.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import shuimin.atws.app.constant.Config;
import shuimin.atws.core.kernel.AppContext;
import shuimin.atws.core.kernel.resource.DefaultModule;
import shuimin.atws.core.kernel.resource.DirectoryResource;
import shuimin.atws.core.kernel.resource.Resource;
import shuimin.atws.core.kernel.resource.ResourceFactory;
import shuimin.atws.util.StrUtils;

import com.shuimin.base.A;
import com.shuimin.base.F;
import com.shuimin.base.logger.Logger;

/**
 * 
 * @author Edwin
 */
public class ModuleBuilder
{
	public static void build(Resource rootResource, String mid,
		String mpkgname)
	{
		new ModuleBuilderCP(mid, mpkgname).build(rootResource);
	}
}

// class ModuleBuilderJar{
// AppContext ctx = AppContext.getInstance();
// Logger logger = ctx.logger;
// String mid;
// String absModulePath;
// String fullClassName;
// String pkgName;
// File moduleDir;
// }
class ModuleBuilderCP
{

	AppContext ctx = AppContext.instance();
	Logger logger = ctx.logger;
	String mid;
	String absModulePath;
	String pkgName;
	File moduleDir;
	final static String[] suffixes;
	static {
		String suffixes_str = AppContext.instance().settings
			.getProperty(Config.SETTINGS_KEY_RESOURCE_FILE_SUFFIXES);
		if (StrUtils.isBlank(suffixes_str)) {
			suffixes_str = Config.RESOURCE_FILE_SUFFIXES_DEFAULT;
		}
		suffixes = suffixes_str.split(",");
	}

	ModuleBuilderCP(String mid, String mpkgname)
	{
		this.mid = mid;
		this.pkgName = mpkgname + "." + mid;
		this.absModulePath = ctx.ROOT_PATH + "/"
			+ pkgName.replaceAll("\\.", "/");
		logger.debug("Module Diretory Path : " + absModulePath);
		moduleDir = new File(absModulePath);
	}

	public void build(Resource rootResource)
	{
		A._assert(moduleDir, "null dir");

		DefaultModule _module = _buildModule(rootResource);

		A._assert(_module, "null _module");
		_build_recur(_module, moduleDir, pkgName);

	}

	private void _build_recur(Resource parent_resource, File f,
		String classNamePrefix)
	{
		A._assert(f, "null dir");
		logger.debug("building DirResource under pkg: "
			+ classNamePrefix);
		/* reading content */
		for (File clazzF : f.listFiles()) {
			logger.debug("file:" + clazzF.getAbsolutePath());
			if (clazzF.isDirectory()) {
				DirectoryResource dirResource = _buildDirectoryResource(parent_resource);
				A._assert(dirResource,
					"resource null, build not success");
				dirResource.name(clazzF.getName());
				_build_recur(
					dirResource,
					clazzF,
					classNamePrefix + "."
						+ clazzF.getName());
				continue;
			}
			if (clazzF.isFile()
				&& StrUtils.notBlank(clazzF.getName())) {
				/* build as class */
				if (clazzF.getName().endsWith(".class")) {
					String classFullName = classNamePrefix
						+ "." + clazzF.getName();
					logger.debug("find class :"
						+ classFullName);
					_buildRegularResource(parent_resource,
						classFullName);
					continue;
				}
				/* build as File */
				String[] name = F.splitSuffixName(f);
				if (name[1] != null
					&& -1 != A.ArrayContains(suffixes,
						name[1])) {
					_buildFileResource(parent_resource, f);
				}
			}
		}
	}

	private void _buildFileResource(Resource parent, File f)
	{

		try {
			ResourceFactory.build("fileResourceBuilder",
				new Object[] { parent, f });
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private DefaultModule _buildModule(Resource root)
	{
		FileInputStream fis = null;
		try {
			DefaultModule m = (DefaultModule) ResourceFactory
				.build("directoryResourceBuilder",
					new Object[] { root,
						DefaultModule.class });
			File f = new File(moduleDir, "config");
			Properties config = new Properties();
			fis = new FileInputStream(f);
			if (f.canRead()) {
				config.load(fis);
			}
			m.name(mid);
			m.attrs().putAll(config);
			return m;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (fis != null)
					fis.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private DirectoryResource _buildDirectoryResource(Resource parent)
	{
		try {
			return (DirectoryResource) ResourceFactory.build(
				"directoryResourceBuilder", new Object[] {
					parent, DirectoryResource.class });
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void _buildRegularResource(Resource parent, String className)
	{
		try {
			Class<?> c = Class.forName(className.substring(0,
				className.length() - 6));
			ResourceFactory.build("regularResourceBuilder",
				new Object[] { parent, c });
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.err(e);
		}
	}
}
