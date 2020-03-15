package ahualy.neepu.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer con_id;
	private Integer sal_id;
	private Integer che_id;
	private Integer user_id;
	private Dept dept;
	private Job job;
	private User user;
	private String name;
	private String card_id;
	private String address;
	private String phone;
	private Sex sex;
	private Education education;
	
	//关于时间的格式转换
	private Date createdate;
	private String creatTimeStr;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public Employee(){
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
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

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
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

	public Integer getChe_id() {
		return che_id;
	}

	public void setChe_id(Integer che_id) {
		this.che_id = che_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	
	
}
