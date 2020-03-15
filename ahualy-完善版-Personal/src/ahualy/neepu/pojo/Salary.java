package ahualy.neepu.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Salary implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Dept dept;

    private Job job;

    private Employee employee;

    private Integer salaryStation;

    private Integer salaryLevel;

    private Integer seniorityPay;

    private Integer performance;

    private Float individualIncome;

    private Integer sex;

  //关于时间的格式转换
  	private Date createdate;
    private String creatTimeStr;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Salary() {
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

	

    public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getSalaryStation() {
        return salaryStation;
    }

    public void setSalaryStation(Integer salaryStation) {
        this.salaryStation = salaryStation;
    }

    public Integer getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(Integer salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public Integer getSeniorityPay() {
        return seniorityPay;
    }

    public void setSeniorityPay(Integer seniorityPay) {
        this.seniorityPay = seniorityPay;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Float getIndividualIncome() {
        return individualIncome;
    }

    public void setIndividualIncome(Float individualIncome) {
        this.individualIncome = individualIncome;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

   

	
    
    
}