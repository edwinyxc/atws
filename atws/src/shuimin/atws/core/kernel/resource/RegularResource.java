package shuimin.atws.core.kernel.resource;

import shuimin.atws.core.kernel.AppContext;

import com.shuimin.base.logger.Logger;

public class RegularResource extends AbstractResource {

        final Logger logger = AppContext.instance().logger;
        
        /**
         * protected  
         */
        protected  RegularResource()
	{
	}
        
        
        @Override
        public Object exec(Object x) throws Exception
        {
                throw new RuntimeException("you do not exe a regular resource");
//                Call call = (Call) x;//TODO
//                action.forward("/" + path);
//                return this;
        }

        @Override
        public Object write(Object w) throws Exception
        {
                throw new RuntimeException("you do not write a regular resource");
        }

        @Override
        public Object read(Object r) throws Exception
        {
        	return this;
        }

	@Override
	public Resource select(String name)
	{
		return null;//leaf resource
	}
        
}
