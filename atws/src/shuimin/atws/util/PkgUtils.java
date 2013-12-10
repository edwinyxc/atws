package shuimin.atws.util;

import com.shuimin.base.A;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.shuimin.base.logger.Logger;

import shuimin.atws.core.kernel.AppContext;

public class PkgUtils
{
	private static Logger logger = AppContext.instance().logger;

	public static List<String> getModulesFromPkg(String pkgName)
	{
		List<Package> pkgs = getPackagesUnder(pkgName);
		logger.info("pkgs:\n");
		logger.info(pkgs);
		List<String> ret = new ArrayList<String>();
		for (Package p : pkgs) {
			String str = p.getName();
			char ch;
			StringBuilder sb = new StringBuilder();
			for (int i = str.length() - 1; i >= 0; i--) {
				if ((ch = str.charAt(i)) != '.') {
					sb.insert(0, ch);
				}
				else {
					break;
				}
			}
			ret.add(sb.toString());
		}
		return ret;
	}
//        public static List<String> getModulesInJar(String abs_jar_file_path) throws IOException
//        {
//                JarFile jar = new JarFile(abs_jar_file_path);
//                JarEntry entry;
//                Enumeration<JarEntry> entries = jar.entries();
//                while(entries.hasMoreElements()){
//                        entry = jar.entries().nextElement();
//                        if(entry.isDirectory() && !fileNameIgnored(entry.getName())){
//                                List<String> moduals = new ArrayList<>();
//                        }
//                }
//        }
        
        private static final String[] ignored = new String[]{"fw","app","sys"};
        private static boolean fileNameIgnored(String fileName){
                A._assert(fileName, "param err file name null");
                if(A.ArrayContains(ignored, fileName) != -1){
                        return true;
                }
                return false;
        }
	/**
	 * 寻找在一个文件夹下的所有module
	 * 
	 * @param abs_root_dir_path
	 * @return
	 */
	public static List<String> getModulesUnderFolder(String abs_root_dir_path)
	{
		List<String> moduals = new ArrayList<String>();
		File rootDir = new File(abs_root_dir_path);
                logger.debug(abs_root_dir_path);
		if (rootDir.isDirectory()) {
			File[] files = rootDir.listFiles();
                        logger.debug(files);
			for (File f : files) {
				String modName = f.getName();
				if (f.isDirectory() && !fileNameIgnored(modName)) {
					moduals.add(modName);
				}
			}
		}
		return moduals;
	}

	public static List<String> getClasses(String abs_bo_dir_path)
	{
		List<String> ret = new ArrayList<String>();
		File rootDir = new File(abs_bo_dir_path);
		if (rootDir.isDirectory()) {
			File[] files = rootDir.listFiles();
			for (File f : files) {
				String fName = f.getName();
				if (!f.isDirectory() && fName.endsWith(".class") && !fName.contains("$")) {
					ret.add(fName.substring(0, fName.length() - 6));
				}
			}
		}
		return ret;
	}

//	public static String getMid(Object o)
//	{
//		if (o instanceof Dao) {
//			String classFullName = o.getClass().getName();
//			String[] tmp = classFullName.split("\\.");
//			for (int i = tmp.length - 1; i >= 0; i--) {
//				if (tmp[i].equals("dao")) {
//					i--;
//					if (i >= 0) {
//						return tmp[i];
//					}
//				}
//			}
//		}
//		else if (o instanceof Service) {
//			String classFullName = o.getClass().getName();
//			String[] tmp = classFullName.split("\\.");
//			for (int i = tmp.length - 1; i >= 0; i--) {
//				if (tmp[i].equals("service")) {
//					i--;
//					if (i >= 0) {
//						return tmp[i];
//					}
//				}
//			}
//		}
//		return null;
//	}

	/**
	 * 只获得当前下一层，不会递归
	 * 
	 * @param p
	 * @return
	 */
	public static List<Package> getPackagesUnder(String pkgName)
	{
		List<Package> ret = new ArrayList<Package>();
		Package[] pkgs = Package.getPackages();
		String pkn;
		for (Package pk : pkgs) {
			pkn = pk.getName();
			if (pkn.startsWith(pkgName)) {
				ret.add(pk);
			}
		}
		return ret;
	}

	@SuppressWarnings("unused")
	private static boolean _packageChecker(String e, String p)
	{
		boolean a = e.startsWith(p);
		if (!a)
			return false;
		char c;
		int count = 0;
		for (int i = p.length() - 1; i < e.length(); i++) {
			c = e.charAt(i);
			if (c == '.') {
				count++;
			}
		}
		if (count == 1)
			return true;
		return false;
	}

	/**
	 * servicePath like 'people/sth.'
	 * 
	 * @param mid
	 * @param servicePath
	 * @return
	 */
//	public static String getServiceClassName(String mid, String[] path)
//	{
//		StringBuilder ret = new StringBuilder();
//		ret.append(App.modulePkgName()).append(".").append(mid).append(".service");
//		for (String s : path) {
//			ret.append(".").append(s);
//		}
//		return ret.toString();
//	}

//	public static String getBoClassName(String mid, String boSimpleName)
//	{
//		StringBuilder ret = new StringBuilder();
//		ret.append(App.modulePkgName()).append(".").append(mid).append(".bo.").append(boSimpleName);
//		return ret.toString();
//	}
}
