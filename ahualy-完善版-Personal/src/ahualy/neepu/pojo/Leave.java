package ahualy.neepu.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Leave implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Employee employee;
	
	private Dept dept;
	
	private Job job;
	
	private String  startdata;
	
	private String enddata;
	
	private Integer leavedays;
	
	private String content;
	
	private LeaveType leavetype;
	
	private LeaveStatus leavestatus;
	
	//关于时间的格式转换
  	private Date leavetime;
    private String creatTimeStr;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	
	
	public String getStartdata() {
		return startdata;
	}

	public void setStartdata(String startdata) {
		this.startdata = startdata;
	}

	public String getEnddata() {
		return enddata;
	}

	public void setEnddata(String enddata) {
		this.enddata = enddata;
	}

	public Integer getLeavedays() {
		return leavedays;
	}

	public void setLeavedays(Integer leavedays) {
		this.leavedays = leavedays;
	}

	

	public LeaveType getLeavetype() {
		return leavetype;
	}

	public void setLeavetype(LeaveType leavetype) {
		this.leavetype = leavetype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LeaveStatus getLeavestatus() {
		return leavestatus;
	}

	public void setLeavestatus(LeaveStatus leavestatus) {
		this.leavestatus = leavestatus;
	}

	public Date getLeavetime() {
		return leavetime;
	}

	public void setLeavetime(Date leavetime) {
		this.leavetime = leavetime;
	    String time = sdf.format(leavetime);
	    setCreatTimeStr(time);
	}

	public String getCreatTimeStr() {
		return creatTimeStr;
	}

	public void setCreatTimeStr(String creatTimeStr) {
		this.creatTimeStr = creatTimeStr;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	@Override
	public String toString() {
		return "Leave [id=" + id + ", employee=" + employee + ", dept=" + dept + ", job=" + job + ", startdata="
				+ startdata + ", enddata=" + enddata + ", leavedays=" + leavedays + ", content=" + content
				+ ", leavetype=" + leavetype + ", leavestatus=" + leavestatus + ", leavetime=" + leavetime
				+ ", creatTimeStr=" + creatTimeStr + ", sdf=" + sdf + "]";
	}

	
	


	
	

}
