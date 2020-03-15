package ahualy.neepu.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contract implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Dept dept;

    private Job job;

    private Employee employee;

    private Traincontract trainContract;

    private Laborcontract laborContract;

    private Confidentialitycontract confidentialityContract;

  //关于时间的格式转换
  	private Date createdate;
    private String creatTimeStr;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public Traincontract getTrainContract() {
		return trainContract;
	}

	public void setTrainContract(Traincontract trainContract) {
		this.trainContract = trainContract;
	}

	public Laborcontract getLaborContract() {
		return laborContract;
	}

	public void setLaborContract(Laborcontract laborContract) {
		this.laborContract = laborContract;
	}

	public Confidentialitycontract getConfidentialityContract() {
		return confidentialityContract;
	}

	public void setConfidentialityContract(Confidentialitycontract confidentialityContract) {
		this.confidentialityContract = confidentialityContract;
	}
    
    
}