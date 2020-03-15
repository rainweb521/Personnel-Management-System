package ahualy.neepu.pojo;

import java.io.Serializable;

public class UserVisit implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private Integer id;
	 private String login_time;
	 private String exit_time;
	 private String visit_ip;
	 private String user_address;
	 private String user_from;
	 //private String visit_url;
	 private String browser;
	 private String system;
	 private String version;
	 private String loginname;
	 private String iphone;
	 
	 
	 
	public UserVisit() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	public String getVisit_ip() {
		return visit_ip;
	}
	public void setVisit_ip(String visit_ip) {
		this.visit_ip = visit_ip;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_from() {
		return user_from;
	}
	public void setUser_from(String user_from) {
		this.user_from = user_from;
	}
	
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	public String getIphone() {
		return iphone;
	}
	public void setIphone(String iphone) {
		this.iphone = iphone;
	}
	public String getExit_time() {
		return exit_time;
	}
	public void setExit_time(String exit_time) {
		this.exit_time = exit_time;
	}
	@Override
	public String toString() {
		return "UserVisit [id=" + id + ", login_time=" + login_time + ", exit_time=" + exit_time + ", visit_ip="
				+ visit_ip + ", user_address=" + user_address + ", user_from=" + user_from + ", browser=" + browser
				+ ", system=" + system + ", version=" + version + ", loginname=" + loginname + ", iphone=" + iphone
				+ "]";
	}
	
	
	
	
 
	
}
