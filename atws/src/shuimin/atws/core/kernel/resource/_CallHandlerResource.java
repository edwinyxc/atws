package shuimin.atws.core.kernel.resource;
//package shuimin.atws.fw.core.kernel.resource;
//
//import shuimin.atws.fw.core.kernel.call.Call;
//import shuimin.atws.fw.core.kernel.call.CallHandler;
//
//public class CallHandlerResource extends AbstractResource {
//
//        protected CallHandler handler;
//
//        public CallHandlerResource(CallHandler handler)
//        {
//                this.handler = handler;
//                this.path = handler.path();
//        }
//
//        @Override
//        public Object exec(Object x) throws Exception
//        {
//                Call call = (Call) x;
//                handler.response(call);
//                return this;
//        }
//
//        @Override
//        public Object write(Object w) throws Exception
//        {
//                throw new RuntimeException("you do not write an call handler");
//        }
//
//        @Override
//        public Object read(Object r) throws Exception
//        {
//                return handler;
//        }
//}
