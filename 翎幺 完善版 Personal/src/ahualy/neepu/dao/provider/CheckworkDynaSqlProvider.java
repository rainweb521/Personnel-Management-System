package ahualy.neepu.dao.provider;


import static ahualy.neepu.util.common.Constants.CHECKWORKTABLE;

import java.util.Map;


import org.apache.ibatis.jdbc.SQL;

import ahualy.neepu.pojo.Checkwork;
public class CheckworkDynaSqlProvider {
	
	
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(CHECKWORKTABLE);
				if(params.get("checkwork") != null){
					Checkwork checkwork = (Checkwork) params.get("checkwork");
					if(checkwork.getDept() != null && checkwork.getDept().getId() != null && checkwork.getDept().getId() != 0){
						WHERE(" DEPT_ID = #{checkwork.dept.id} ");
					}
					if(checkwork.getJob() != null && checkwork.getJob().getId() != null && checkwork.getJob().getId() != 0){
						WHERE(" JOB_ID = #{checkwork.job.id} ");
					}
					if(checkwork.getEmployee() != null && !checkwork.getEmployee().equals("")){
						WHERE("  EMP_ID LIKE CONCAT ('%',#{checkwork.employee.id},'%') ");
					}
					if(checkwork.getWorkingdays()!= null && !checkwork.getWorkingdays().equals("")){
						WHERE(" WORKINGDAYS LIKE CONCAT ('%',#{checkwork.workingdays},'%') ");
					}
					if(checkwork.getDaysleave()!= null && !checkwork.getDaysleave().equals("") ){
						WHERE(" DAYSLEAVE LIKE CONCAT ('%',#{checkwork.daysleave},'%') ");
					}
					if(checkwork.getDaysout()!= null && !checkwork.getDaysout().equals("") ){
						WHERE(" daysout LIKE CONCAT ('%',#{checkwork.daysout},'%') ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	

	// 动态插入
				public String insert_Checkwork(Checkwork checkwork){
					
					return new SQL(){
						{
							INSERT_INTO(CHECKWORKTABLE);
							if(checkwork.getId()!=null) {
								VALUES("id", "#{employee.id}");
							}
							if(checkwork.getDept() != null){
								VALUES("DEPT_ID", "#{dept.id}");
							}
							if(checkwork.getJob() != null){
								VALUES("JOB_ID", "#{job.id}");
							}
							if(checkwork.getEmployee() != null){
								VALUES("EMP_ID", "#{employee.id}");
							}
							if(checkwork.getWorkingdays() != null){
								VALUES("WORKINGDAYS", "#{workingdays}");
							}
							if(checkwork.getDaysleave() != null){
								VALUES("DAYSLEAVE", "#{daysleave}");
							}
							if(checkwork.getDaysout() != null){
								VALUES("DAYSOUT", "#{daysout}");
							}
						}
					}.toString();
					
				}	
				
				
				// 动态查询总数量
				public String count(Map<String, Object> params){
					return new SQL(){
						{
							SELECT("count(*)");
							FROM(CHECKWORKTABLE);
							if(params.get("checkwork") != null){
								Checkwork checkwork = (Checkwork) params.get("checkwork");
								if(checkwork.getDept() != null && checkwork.getDept().getId() != null && checkwork.getDept().getId() != 0){
									WHERE(" DEPT_ID = #{checkwork.dept.id} ");
								}
								if(checkwork.getJob() != null && checkwork.getJob().getId() != null && checkwork.getJob().getId() != 0){
									WHERE(" JOB_ID = #{checkwork.job.id} ");
								}
								if(checkwork.getEmployee() != null && !checkwork.getEmployee().equals("")){
									WHERE(" EMP_ID = #{checkwork.employee.id}");
								}
								if(checkwork.getWorkingdays()!= null && !checkwork.getWorkingdays().equals("")){
									WHERE(" WORKINGDAYS LIKE CONCAT ('%',#{checkwork.workingdays},'%') ");
								}
								if(checkwork.getDaysleave()!= null && !checkwork.getDaysleave().equals("") ){
									WHERE(" DAYSLEAVE LIKE CONCAT ('%',#{checkwork.daysleave},'%') ");
								}
								if(checkwork.getDaysout()!= null && !checkwork.getDaysout().equals("") ){
									WHERE(" daysout LIKE CONCAT ('%',#{checkwork.daysout},'%') ");
								}
							}
						}
					}.toString();
				}	
				
				// 动态更新
				public String update_Checkwork(Checkwork checkwork){
					
					return new SQL(){
						{
							UPDATE(CHECKWORKTABLE);
							
							if(checkwork.getWorkingdays()!= null){
								SET("WORKINGDAYS = #{workingdays}");
							}
							if(checkwork.getDaysleave()!= null){
								SET("DAYSLEAVE = #{daysleave}");
							}
							if(checkwork.getDaysout()!= null){
								SET("DAYSOUT = #{daysout}");
							}
							if(checkwork.getDept()!= null){
								SET("DEPT_ID = #{dept.id}");
							}
							if(checkwork.getJob()!= null){
								SET("JOB_ID = #{job.id}");
							}
							WHERE("id = #{id}");
						}
					}.toString();
				}
}
