package shuimin.atws.core.kernel.user;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Edwin
 */
public class User {
        public int uid;
        public String name;
        public String loginName;
        public String lastPath;
        public String homePath;
        public int gid;
        final public Map<String,String> attrs = new TreeMap<String, String>();
        public String passShadow;
}