package ahualy.neepu.dao.provider;

import static ahualy.neepu.util.common.Constants.EMPLOYEETABLE;

import java.util.Map;


import org.apache.ibatis.jdbc.SQL;

import ahualy.neepu.pojo.Employee;

public class EmployeeDynaSqlProvider {
	
	
	// 分页动态查询
		public String selectWhitParam(Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(EMPLOYEETABLE);
					if(params.get("employee") != null){
						Employee employee = (Employee)params.get("employee");
						if(employee.getDept() != null && employee.getDept().getId() != null && employee.getDept().getId() != 0){
							WHERE(" DEPT_ID = #{employee.dept.id} ");
						}
						if(employee.getJob() != null && employee.getJob().getId() != null && employee.getJob().getId() != 0){
							WHERE(" JOB_ID = #{employee.job.id} ");
						}
						if(employee.getName() != null && !employee.getName().equals("")){
							WHERE("  NAME LIKE CONCAT ('%',#{employee.name},'%') ");
						}
						if(employee.getPhone() != null && !employee.getPhone().equals("")){
							WHERE(" phone LIKE CONCAT ('%',#{employee.phone},'%') ");
						}
						if(employee.getSex()!= null && employee.getSex().getId() != 0){
							WHERE("sex_id = #{employee.sex.id}");
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
					FROM(EMPLOYEETABLE);
					if(params.get("employee") != null){
						Employee employee = (Employee)params.get("employee");
						if(employee.getDept() != null && employee.getDept().getId() != null && employee.getDept().getId() != 0){
							WHERE(" DEPT_ID = #{employee.dept.id} ");
						}
						if(employee.getJob() != null && employee.getJob().getId() != null && employee.getJob().getId() != 0){
							WHERE(" JOB_ID = #{employee.job.id} ");
						}
						if(employee.getName() != null && !employee.getName().equals("")){
							WHERE("  NAME LIKE CONCAT ('%',#{employee.name},'%') ");
						}
						if(employee.getPhone() != null && !employee.getPhone().equals("")){
							WHERE(" phone LIKE CONCAT ('%',#{employee.phone},'%') ");
						}
						
						if(employee.getSex()!= null && employee.getSex().getId() != 0){
							WHERE("sex_id = #{employee.sex.id}");
						}
					}
				}
			}.toString();
		}	
		
		
		
		
			// 动态插入
			public String insert_Employee(Employee employee){
				
				return new SQL(){
					{
						INSERT_INTO(EMPLOYEETABLE);
						if(employee.getId()!=null) {
							VALUES("id", "#{id}");
						}
						if(employee.getName() != null ){
							VALUES("name", "#{name}");
						}
						if(employee.getCard_id()!=null){
							VALUES("card_id","#{card_id}");
						}
						
						if(employee.getUser_id()!=null){
							VALUES("user_id","#{user_id}");
						}
					
						if(employee.getAddress()!=null){
							VALUES("address","#{address}");
						}
						if(employee.getPhone()!=null){
							VALUES("phone","#{phone}");
						}
						if(employee.getEducation()!=null) {
							VALUES("education_id","#{education.id}");
						}
						
						if(employee.getSex()!=null){
							VALUES("sex_id","#{sex.id}");
						}
						if(employee.getDept()!=null){
							VALUES("dept_id","#{dept.id}");
						}
						if(employee.getJob()!=null){
							VALUES("job_id","#{job.id}");
						}
					}
				}.toString();
			}	
			// 动态更新
			public String update_Employee(Employee job){
				
				return new SQL(){
					{
						UPDATE(EMPLOYEETABLE);
						if(job.getName() != null ){
							SET("name = #{name}");
						}
						if(job.getCard_id()!=null){
							SET("card_id = #{card_id}");
						}
						if(job.getAddress()!=null){
							SET("address = #{address}");
						}
						
						
						if(job.getPhone()!=null){
							SET("phone = #{phone}");
						}
						
						
						if(job.getSex()!=null){
							SET("sex_id = #{sex.id}");
						}
						
						if(job.getEducation()!=null){
							SET("education_id = #{education.id}");
						}
						
						if(job.getCreatedate()!=null){
							SET("createdate = #{createdate}");
						}
						if(job.getDept()!=null){
							SET("dept_id = #{dept.id}");
						}
						if(job.getJob()!=null){
							SET("job_id = #{job.id}");
						}
						
						WHERE(" id = #{id} ");
					}
				}.toString();
			}
}
