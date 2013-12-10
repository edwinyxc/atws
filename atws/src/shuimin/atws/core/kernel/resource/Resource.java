package shuimin.atws.core.kernel.resource;

import com.shuimin.base.abstraction.Namable;


/**

 * anything can be accessed by URI is a Resource
 * Resource is built during runtime 
 * @author ed
 *
 */
public interface Resource extends Namable<Resource>, ResourceTreeNodeAware
{
        /**
         * return the path of this Resource, it is also a part of uri
         * @return 
         */
        public String[] path();
        
        /**
         * select resource under it
         * @return
         */
        public Resource select(String name);
                
        /**
         * execute this resource if possible
         * @param x
         * @return
         * @throws Exception 
         */
        public Object exec(Object x) throws Exception;
        
        /**
         * wirte something to this resource
         * @param w
         * @return
         * @throws Exception 
         */
        public Object write(Object w) throws Exception;
        
        /**
         * read it out
         * @param r
         * @return
         * @throws Exception 
         */
        public Object read(Object r) throws Exception;
}