package shuimin.atws.core.kernel;

import java.util.List;

import shuimin.atws.core.kernel.module.ModuleBuilder;
import shuimin.atws.core.kernel.resource.Resource;
import shuimin.atws.util.PkgUtils;

import com.shuimin.base.logger.Logger;

/**
 * 
 * @author Edwin
 */
class ResourceLoader
{
	protected static void _loadModules_default(Logger logger,
		final String modulePkgName)
	{
		String path = AppContext.instance().ROOT_PATH
			+ modulePkgName.replaceAll("\\.", "/");
		logger.debug("module path:");
		logger.debug(path);
		List<String> moduleList = PkgUtils.getModulesUnderFolder(path);

		logger.info("initializing module installing sequence ...");

		String mid;
		Resource rootResource = Global.RT.root().value();
		// String moduleFullName;
		for (int i = 0; i < moduleList.size(); i++) {
			mid = moduleList.get(i);
			logger.info("installing mod: " + mid);
			try {
				ModuleBuilder.build(rootResource, mid,
					modulePkgName);
			}
			catch (Exception e) {
				logger.err("installing mod: " + mid + "faild :"
					+ e.getMessage());
				e.printStackTrace();
			}

		}
		logger.info("finished");
	}
}
