package ahualy.neepu.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Checkwork implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Dept dept;

    private Job job;

	private Employee employee;

    private Integer workingdays;

    private Integer daysleave;

    private Integer daysout;

  //关于时间的格式转换
  	private Date createdate;
    private String creatTimeStr;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    
    public Checkwork() {
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

	public Integer getWorkingdays() {
        return workingdays;
    }

    public void setWorkingdays(Integer workingdays) {
        this.workingdays = workingdays;
    }

    public Integer getDaysleave() {
        return daysleave;
    }

    public void setDaysleave(Integer daysleave) {
        this.daysleave = daysleave;
    }

	public Integer getDaysout() {
		return daysout;
	}

	public void setDaysout(Integer daysout) {
		this.daysout = daysout;
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