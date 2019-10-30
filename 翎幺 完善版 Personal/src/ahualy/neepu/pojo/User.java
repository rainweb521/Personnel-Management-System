package ahualy.neepu.pojo;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String loginname;
	private String password;
	private Status status;
	private String email;
	private Integer emp_id;  //关联员工信息，目的是为了得到员工id
	private Integer che_id;  //关联员工考勤信息，目的是为了得到员工id
	private Integer con_id;  //关联员工合同信息，目的是为了得到员工id
	private Integer sal_id;  //关联员工薪酬信息，目的是为了得到员工id
	//关于时间的格式转换
	private Date createdate;
    private String creatTimeStr;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public User(){
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
        this.createdate = createdate;
        String time = sdf.format(createdate);
        setCreatTimeStr(time);
	}
	
	public String getCreatTimeStr() {
		return creatTimeStr;
	}
 
	public void setCreatTimeStr(String creatTimeStr) {
		this.creatTimeStr = creatTimeStr;
	}
	
	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	

	public Integer getChe_id() {
		return che_id;
	}

	public void setChe_id(Integer che_id) {
		this.che_id = che_id;
	}

	public Integer getCon_id() {
		return con_id;
	}

	public void setCon_id(Integer con_id) {
		this.con_id = con_id;
	}

	public Integer getSal_id() {
		return sal_id;
	}

	public void setSal_id(Integer sal_id) {
		this.sal_id = sal_id;
	}
 
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [status=" + status + "]";
	}
	
}
