package shuimin.atws.util;

/**
 * String Utilities
 * Like Simple null String handler;
 * 
 * @author ed
 * 
 */
public class StrUtils
{
	/**
	 * prevent initialization
	 */
	private StrUtils()
	{
	}

	public static boolean isBlank(String str)
	{
		return str == null || "".equals(str.trim()) ? true : false;
	}

	public static boolean notBlank(String str)
	{
		return str == null || "".equals(str.trim()) ? false : true;
	}

	public static boolean notBlank(String... strings)
	{
		if (strings == null)
			return false;
		for (String str : strings)
			if (str == null || "".equals(str.trim()))
				return false;
		return true;
	}

	public static boolean notNull(Object... paras)
	{
		if (paras == null)
			return false;
		for (Object obj : paras)
			if (obj == null)
				return false;
		return true;
	}
	
	/**
	 * 
	 * @param str
	 * @param idx
	 * @return
	 */
	public static int indexOf(String ori, char ch, int idx)
	{
		char c;
		int occur_idx = 0;
		for(int i = 0; i< ori.length();i++){
			c = ori.charAt(i);
			if(c == ch){
				if(occur_idx == idx){
					return i;
				}
				occur_idx++;
			}
		}
		return -1;
	}
}
