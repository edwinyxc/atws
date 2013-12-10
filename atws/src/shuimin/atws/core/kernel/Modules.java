package shuimin.atws.core.kernel;

import java.util.Collection;

import shuimin.atws.core.kernel.module.Module;
import shuimin.atws.core.kernel.module.ModuleNotFoundException;
import shuimin.atws.core.kernel.resource.Resource;

/**
 *
 * @author Edwin
 */
public class Modules {

        private static AppContext ctx = AppContext.instance();

        public static Module find(String mid) throws ModuleNotFoundException
        {
                for (Resource m : all()) {
                        if (m instanceof Module && m.name().equals(mid))
                                return (Module) m;
                }
                return null;
        }

        public static Collection<Resource> all()
        {
                return ctx.rt().root().childrenValues();
        }
        
}
