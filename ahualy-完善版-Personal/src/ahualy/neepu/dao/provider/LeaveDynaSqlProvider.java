package ahualy.neepu.dao.provider;

import static ahualy.neepu.util.common.Constants.LEAVETABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import ahualy.neepu.pojo.Leave;

public class LeaveDynaSqlProvider {
	// 动态插入
	public String insert_Leave(Leave leave){
		return new SQL(){
			{
				INSERT_INTO(LEAVETABLE);
				if (leave.getDept() != null) {
					VALUES("DEPT_ID", "#{dept.id}");
				}
				if (leave.getJob() != null) {
					VALUES("JOB_ID", "#{job.id}");
				}
				if (leave.getEmployee() != null) {
					VALUES("EMP_ID", "#{employee.id}");
				}
				if (leave.getStartdata() != null) {
					VALUES("startdata", "#{startdata}");
				}
				if (leave.getEnddata() != null) {
					VALUES("enddata", "#{enddata}");
				}
				if (leave.getLeavedays()!= null) {
					VALUES("leavedays", "#{leavedays}");
				}
				if (leave.getLeavetype() != null) {
					VALUES("leavetype", "#{leavetype.id}");
				}
				if (leave.getContent()!= null) {
					VALUES("content", "#{content}");
				}
			}
		}.toString();
		
	}	
	
	
	// 动态更新
		public String update_Leave(Leave leave){
			
			return new SQL(){
				{
					UPDATE(LEAVETABLE);
					if(leave.getEmployee() != null){
						SET("EMP_ID = #{employee.id}");
					}
					if(leave.getDept()!= null){
						SET("DEPT_ID = #{dept.id}");
					}
					if(leave.getJob()!= null){
						SET("JOB_ID = #{job.id}");
					}
					
					if (leave.getStartdata() != null) {
						SET(" startdata = #{startdata} ");
					}
					if (leave.getEnddata() != null) {
						SET(" enddata = #{enddata} ");
					}
					
					if(leave.getLeavedays()!= null){
						SET(" leavedays = #{leavedays} ");
					}
					if(leave.getLeavetype()!= null){
						SET(" leavetype = #{leavetype.id} ");
					}
					if(leave.getContent()!= null){
						SET(" content = #{content} ");
					}
					if(leave.getLeavestatus()!=null) {
						SET(" leavestatus = #{leavestatus.id} ");
					}
					WHERE(" id = #{id} ");
				}
			}.toString();
		}
		
		
		// 分页动态查询
				public String selectWhitParam(Map<String, Object> params){
					String sql =  new SQL(){
						{
							SELECT("*");
							FROM(LEAVETABLE);
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
							FROM(LEAVETABLE);
						}
					}.toString();
				}	
}
