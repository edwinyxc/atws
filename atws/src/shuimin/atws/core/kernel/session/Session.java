package shuimin.atws.core.kernel.session;

import com.shuimin.base.abstraction.Attrs;

/**
 * user session abstraction
 * @author ed
 *
 */
public interface Session extends Attrs<Session>
{
	/**
	 * log information
	 * @return
	 */
	public String logInfo();
	
	/**
	 * global session id, used to manage the session
	 * @return
	 */
	public String id();//key id
	
	/**
	 * reset this session e.g. re-acquire user name & pass 
	 */
	public void reset();
	
	/**
	 * dispose a session and log byWho
	 * @param byWho 
	 */
	public void kick(String byWho);
	
}
