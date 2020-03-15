package ahualy.neepu.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Train implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Employee employee;
	
	private Dept dept;
	
	private Job job;
	
	private String content;
	
	private String  startdata;
	
	private String enddata;
	
	private Integer totallength;
	
	private Completion completion;
	
	private Integer grade;
	
	//关于时间的格式转换
  	private Date traintime;
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
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
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
	
	public Integer getTotallength() {
		return totallength;
	}
	
	public void setTotallength(Integer totallength) {
		this.totallength = totallength;
	}
	
	public Completion getCompletion() {
		return completion;
	}
	
	public void setCompletion(Completion completion) {
		this.completion = completion;
	}
	
	public Integer getGrade() {
		return grade;
	}
	
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	public Date getTraintime() {
		return traintime;
	}
	
	public void setTraintime(Date traintime) {
		this.traintime = traintime;
	    String time = sdf.format(traintime);
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
	
}
