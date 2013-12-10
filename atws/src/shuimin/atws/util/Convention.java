/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shuimin.atws.util;

import com.shuimin.base.A;
import com.shuimin.base.X;
import java.beans.Introspector;

/**
 *
 * @author Edwin
 */
public class Convention {

        public static String makeName(Object o)
        {
                return makeName(null, o, null);
        }

        public static String makeName(String prefix, Object o, String suffix)
        {
                String name = Introspector.decapitalize(o.getClass().getSimpleName());
                return "" + prefix + name + suffix;
        }

        public static String nextName(String name)
        {
                A._assert(name, "null name");
                int indexOf_ = name.indexOf("_");
                if (indexOf_ < 0) {
                        return name + "_1";
                } else {
                        int _1 = indexOf_ + 1;
                        if (_1 < name.length()) {
                                String number = name.substring(_1);
                                int x = X.parseInt(number);
                                x++;
                                return name.replace(number, String.valueOf(x));
                        }
                }
                return name;
        }
}
