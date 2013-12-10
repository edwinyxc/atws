package shuimin.atws.core.kernel;

import java.util.Map;
import java.util.Properties;

import shuimin.atws.Const;
import shuimin.atws.core.kernel.resource.ResourceFactory;
import shuimin.atws.core.kernel.resource.ResourceTree;
import shuimin.atws.util.Paths;
import shuimin.atws.util.StrUtils;

import com.shuimin.base.logger.Logger;

/**
 * 系统本身！入口
 * 
 * @author ed
 * 
 */
public class AppContext
{
	public Logger logger;
	public String ROOT_PATH;
	public String ROOT_PKG_PATH;
	public Properties settings = Global.settings;

	public Map<String, Object> Attributes()
	{
		return Global.attributes;
	}

	private AppContext()
	{
	}

	;

	private void _initLoggerLevel()
	{
		String level = settings.getProperty("debug_level");
		if (StrUtils.notBlank(level)) {
			logger.info("setting logger debug level eq " + level);
			if (level.equalsIgnoreCase("debug")) {
				logger.config("default", Logger.DEBUG);
			}
			else if (level.equalsIgnoreCase("info")) {
				logger.config("default", Logger.INFO);
			}
			else if (level.equalsIgnoreCase("error")) {
				logger.config("default", Logger.ERROR);
			}
			else if (level.equalsIgnoreCase("fatal")) {
				logger.config("default", Logger.FATAL);
			}
		}
	}

	public void init(Properties setting)
	{
		logger = Logger.getDefault();
		logger.info("System init ....logger OK");
		logger.info("APPCTX CLASS: " + this.getClass().getCanonicalName());
		if (setting != null)
			settings.putAll(setting);

		_initLoggerLevel();

		_init_resource_builders();

		Global.init();

		ROOT_PATH = Paths.classes;
		logger.info("Root Path: " + ROOT_PATH);
		String r_root_pkg_path = Const.APP.PKG_NAME.replaceAll("\\.", "/");
		settings.put("ROOT_PKG_PATH", r_root_pkg_path);

		ROOT_PKG_PATH = ROOT_PATH + r_root_pkg_path;
		logger.info("Root PKG Path: " + ROOT_PKG_PATH);

		// /**
		// * setting connection pool
		// */
		// cp = _initConnectionPool();
		/**
		 * load system
		 */
		_loadSys();

		/**
		 * load sys-defined modules
		 */
		// _loadModules(Config.ROOT_PKG_NAME,
		// S.arr2List(Config.SYS_PKGS));
		/**
		 * load user-defined modules
		 */
		String modulesPkgName = settings.getProperty(Const.MODULE.PROP_KEY_PKG_NAME);
		logger.debug("modulesPkgName: " + modulesPkgName);
		if (StrUtils.notBlank(modulesPkgName)) {
			ResourceLoader._loadModules_default(logger, modulesPkgName);
		}

		// _loadLangBundles();

		/**
		 * init APS
		 */
		_initAps();
	}

	private void _init_resource_builders()
	{
		logger.info("installing resource builders");
		ResourceFactory.init();
		logger.info("finished");
	}

	private void _loadSys()
	{
	}

	private void _initAps()
	{
	}

	// private void _initDbProps()
	// {
	// logger.info("reading dbProps from " + Config.DB_PROPS_FILE_PATH);
	// try {
	// dbProps.load(new FileInputStream(ROOT_PATH
	// + Config.DB_PROPS_FILE_PATH));
	// }
	// catch (FileNotFoundException e) {
	// logger.fatal(ROOT_PATH + Config.DB_PROPS_FILE_PATH
	// + " does not exist");
	// System.exit(1);
	// }
	// catch (IOException e) {
	// logger.fatal(ROOT_PATH + Config.DB_PROPS_FILE_PATH
	// + " io excep");
	// e.printStackTrace();
	// System.exit(1);
	// }
	// logger.info("reading dbProps OK");
	// }
	// private List<Package> _getModList()
	// {
	// logger.info("searching modules....");
	// String root_pkg_name = modualProps
	// .getProperty(Config.MODUAL_PROPS_ROOT_PKG_NAME);
	// Package root_pkg = SysUtil.getPackage(root_pkg_name);
	// if (root_pkg == null) {
	// logger.fatal("PKG: [" + root_pkg_name + "] not exist");
	// System.exit(1);
	// }
	// List<Package> modualPkgs = SysUtil.getPackagesUnder(root_pkg);
	// return modualPkgs;
	// }
	// private void _loadLangBundles()
	// {
	// logger.info("initializing i18n sequence ...");
	// Properties props = new Properties();
	// logger.info("reading i18n system bundle ...");
	// try {
	// InputStream is =
	// this.getClass().getClassLoader().getResourceAsStream(Config.LANG_BUNDLE_FILE);
	// props.load(is);
	// }
	// catch (FileNotFoundException e) {
	// logger.info("bundle not found...");
	// e.printStackTrace();
	// }
	// catch (IOException e) {
	// logger.info("bundle not found...");
	// e.printStackTrace();
	// }
	// I18N.getInstance().init(props);
	// logger.info("Finished.");
	// }
	private static class Holder
	{

		private static AppContext INSTANCE = new AppContext();
	}

	public static AppContext instance()
	{
		return Holder.INSTANCE;
	}

	public void setLoggerDebugLvl(int lvl)
	{
		logger.config("default", lvl);
	}

	// ------------------getter & setter-------------------------------
	public Logger getLogger()
	{
		return logger;
	}

	// public Map<String, Module> getModualMap()
	// {
	// return moduleMap;
	// }
	//
	// public AjaxPushServer getAps()
	// {
	// return aps;
	// }
	// private ConnectionPool _initConnectionPool()
	// {
	// logger.info("Constructing connection pool.");
	// try {
	// ConnectionPool cp = new ConnectionPool();
	// cp.setLogger(logger);
	// cp.init(settings);
	// return cp;
	// }
	// catch (SQLException e) {
	// e.printStackTrace();
	// }
	// logger.info("skipping cp construction");
	// return null;
	// }
	// private boolean _initDbProps() throws FileNotFoundException
	// {
	// String db_p_path = etcPath + "db.properties";
	// logger.info("reading dbProps from " + db_p_path);
	// try {
	// dbProps.load(new FileInputStream(db_p_path));
	// logger.info("reading dbProps OK");
	// return true;
	// }
	// catch (IOException e) {
	// logger.fatal(db_p_path + " io excep");
	// e.printStackTrace();
	// }
	// return false;
	// }

	public ResourceTree rt()
	{
		return Global.RT;
	}
}
