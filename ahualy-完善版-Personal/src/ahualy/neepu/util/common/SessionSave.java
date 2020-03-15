package ahualy.neepu.util.common;

import java.util.HashMap;


/**
 * 保存用户每次登录时候的session
 * @author anAn
 *
 */
public class SessionSave {
	private static HashMap<String, String> SessionIdSave = new HashMap<String,String>();
 
	public static HashMap<String, String> getSessionIdSave() {
		return SessionIdSave;
	}
 
	public static void setSessionIdSave(HashMap<String, String> sessionIdSave) {
		SessionIdSave = sessionIdSave;
	}
}