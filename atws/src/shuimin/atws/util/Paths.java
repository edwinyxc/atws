/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shuimin.atws.util;

import java.io.File;

import shuimin.atws.core.kernel.AppContext;

/**
 *
 * @author Edwin
 */
public class Paths {
        public final static String classes = getRootAbsPath(AppContext.class);
        
        public final static String web  = _webPath();
        
        public final static String js = _webPath()+"/js/";
        
        public final static String css = _webPath()+"/css/";
        
        public final static String image = _webPath()+"/image/";
        
        public static String getRootAbsPath(Class<?> clazz){
                return clazz.getClassLoader().getResource("").getPath();
        }
        
        private static String _webPath(){
                File f = new File(classes);
                File WEB_INF = new File(f.getParent());
                return WEB_INF.getParent();
        }
        
        public static String makeRelative(String path){
                if(path == null)return null;
                int lastIndex = path.lastIndexOf(web);
                return path.substring(lastIndex + web.length());
        }
}

