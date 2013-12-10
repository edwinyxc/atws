package shuimin.atws.util;
//package shuimin.atws.fw.util;
//
////import java.util.ArrayList;
////import java.util.HashMap;
////import java.util.List;
//import java.util.Map;
//
//import net.sf.json.JSONObject;
//
//public class JSONUtils
//{
//	// public static Map mapFromString(String s) {
//	// }
//	public static JSONObject map2Json(Map<String, Object> map)
//	{
//		JSONObject json = JSONObject.fromObject(map);
//		return json;
//	}
//
//	public static Map<String, Object> json2Map(String jsonString)
//		throws Exception
//	{
//		try {
//			JSONObject json = JSONObject.fromObject(jsonString);
//			@SuppressWarnings("unchecked")
//			Map<String, Object> myMap = (Map<String, Object>) JSONObject
//				.toBean(json, Map.class);
//			return myMap;
//		}
//		catch (Exception e) {
//			throw e;
//		}
//	}
//
//	// /**
//	// * test
//	// *
//	// * @param args
//	// */
//	// public static void main(String[] args)
//	// {
//	// List list = new ArrayList();
//	// list.add("first");
//	// list.add("second");
//	// Map tmp = new HashMap();
//	// tmp.put("data", list);
//	// // System.out.println(map2Json(tmp).toString());
//	// }
//}