package shuimin.atws.core.kernel.resource;

/**
 *
 * @author Edwin
 */
public class ResourceNotFoundException extends Exception{
        /**
		 * 
		 */
		private static final long serialVersionUID = 6345335059511521891L;
		
		public Resource lastFound;

        public ResourceNotFoundException(Resource r)
        {
                this.lastFound = r;
        }

        public ResourceNotFoundException(Resource r, String message)
        {
                super(message);
                this.lastFound = r;
        }

        public ResourceNotFoundException(String message)
        {
                super(message);
        }
        
}
