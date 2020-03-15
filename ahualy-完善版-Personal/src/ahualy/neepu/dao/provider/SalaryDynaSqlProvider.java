package ahualy.neepu.dao.provider;

import static ahualy.neepu.util.common.Constants.SALARYTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import ahualy.neepu.pojo.Salary;

public class SalaryDynaSqlProvider {
	
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(SALARYTABLE);
				if(params.get("salary") != null){
					Salary salary = (Salary) params.get("salary");
					if(salary.getDept() != null && salary.getDept().getId() != null && salary.getDept().getId() != 0){
						WHERE(" DEPT_ID = #{salary.dept.id} ");
					}
					if(salary.getJob() != null && salary.getJob().getId() != null && salary.getJob().getId() != 0){
						WHERE(" JOB_ID = #{salary.job.id} ");
					}
					if(salary.getEmployee() != null && !salary.getEmployee().equals("")){
						WHERE("  EMP_ID LIKE CONCAT ('%',#{salary.employee.id},'%') ");
					}
					if(salary.getSalaryStation() != null && !salary.getSalaryStation().equals("")){
						WHERE(" SALARY_STATION LIKE CONCAT ('%',#{salary.salaryStation},'%') ");
					}
					if(salary.getSalaryLevel()!= null && !salary.getSalaryLevel().equals("") ){
						WHERE(" SALARY_LEVEL LIKE CONCAT ('%',#{salary.salaryLevel},'%') ");
					}
					if(salary.getSeniorityPay()!= null && !salary.getSeniorityPay().equals("") ){
						WHERE(" SENIORITY_PAY LIKE CONCAT ('%',#{salary.seniorityPay},'%') ");
					}
					if(salary.getPerformance()!= null && !salary.getPerformance().equals("") ){
						WHERE(" PERFORMANCE LIKE CONCAT ('%',#{salary.performance},'%') ");
					}
					if(salary.getIndividualIncome()!= null && !salary.getIndividualIncome().equals("") ){
						WHERE(" INDIVIDUAL_INCOME LIKE CONCAT ('%',#{salary.individualIncome},'%') ");
					}
					if(salary.getSex()!= null && salary.getSex() != 0){
						WHERE("sex = #{salary.sex}");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	
	// 动态查询总数量
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(SALARYTABLE);
				if(params.get("salary") != null){
					Salary salary = (Salary) params.get("salary");
					if(salary.getDept() != null && salary.getDept().getId() != null && salary.getDept().getId() != 0){
						WHERE(" DEPT_ID = #{salary.dept.id} ");
					}
					if(salary.getJob() != null && salary.getJob().getId() != null && salary.getJob().getId() != 0){
						WHERE(" JOB_ID = #{salary.job.id} ");
					}
					if(salary.getEmployee() != null && !salary.getEmployee().equals("")){
						WHERE("  EMP_ID LIKE CONCAT ('%',#{salary.employee.id},'%') ");
					}
					if(salary.getSalaryStation() != null && !salary.getSalaryStation().equals("")){
						WHERE(" SALARY_STATION LIKE CONCAT ('%',#{salary.salaryStation},'%') ");
					}
					if(salary.getSalaryLevel()!= null && !salary.getSalaryLevel().equals("") ){
						WHERE(" SALARY_LEVEL LIKE CONCAT ('%',#{salary.salaryLevel},'%') ");
					}
					if(salary.getSeniorityPay()!= null && !salary.getSeniorityPay().equals("") ){
						WHERE(" SENIORITY_PAY LIKE CONCAT ('%',#{salary.seniorityPay},'%') ");
					}
					if(salary.getPerformance()!= null && !salary.getPerformance().equals("") ){
						WHERE(" PERFORMANCE LIKE CONCAT ('%',#{salary.performance},'%') ");
					}
					if(salary.getIndividualIncome()!= null && !salary.getIndividualIncome().equals("") ){
						WHERE(" INDIVIDUAL_INCOME LIKE CONCAT ('%',#{salary.individualIncome},'%') ");
					}
					if(salary.getSex()!= null && salary.getSex() != 0){
						WHERE("sex = #{salary.sex}");
					}
				}
			}
		}.toString();
	}	


	public String insert_Salary(Salary salary){
		
		return new SQL(){
			{
				INSERT_INTO(SALARYTABLE);
				if(salary.getId()!=null) {
					VALUES("id", "#{employee.id}");
				}
				if(salary.getDept() != null){
					VALUES(" DEPT_ID" , "#{dept.id}");
				}
				if(salary.getJob()!= null){
					VALUES(" JOB_ID" , "#{job.id}");
				}
				if(salary.getEmployee() != null){
					VALUES(" EMP_ID" , "#{employee.id}");
				}
				if(salary.getSalaryStation() != null){
					VALUES(" SALARY_STATION" , "#{salaryStation}");
				}
				if(salary.getSalaryLevel() != null){
					VALUES(" SALARY_LEVEL" , "#{salaryLevel}");
				}
				if(salary.getSeniorityPay() != null){
					VALUES(" SENIORITY_PAY" , "#{seniorityPay}");
				}
				if(salary.getPerformance() != null){
					VALUES(" PERFORMANCE" , "#{performance}");
				}
				if(salary.getIndividualIncome() != null){
					VALUES(" INDIVIDUAL_INCOME" , "#{individualIncome}");
				}
				if(salary.getSex() != null){
					VALUES(" SEX" , "#{sex}");
				}
			}
		}.toString();
	}	
	// 动态更新
	public String update_Salary(Salary salary){
		
		return new SQL(){
			{
				UPDATE(SALARYTABLE);
				if(salary.getEmployee() != null){
					SET("EMP_ID = #{employee.id}");
				}
				if(salary.getDept()!= null){
					SET("dept_id = #{dept.id}");
				}
				if(salary.getJob()!= null){
					SET("job_id = #{job.id}");
				}
				if(salary.getSalaryStation()!= null){
					SET(" SALARY_STATION = #{salaryStation} ");
				}
				if(salary.getSalaryLevel()!= null){
					SET(" SALARY_LEVEL = #{salaryLevel} ");
				}
				if(salary.getSeniorityPay()!= null){
					SET(" SENIORITY_PAY = #{seniorityPay} ");
				}
				if(salary.getPerformance()!= null){
					SET(" PERFORMANCE = #{performance} ");
				}
				if(salary.getIndividualIncome()!= null){
					SET(" INDIVIDUAL_INCOME = #{individualIncome} ");
				}
				if(salary.getSex()!= null){
					SET(" SEX = #{sex} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}
