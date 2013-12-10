package shuimin.atws.core.kernel.user;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Edwin
 */
public class Group {
        public int gid;
        public String name;
        final public Map<String,String> attrs = new TreeMap<String, String>();
}
