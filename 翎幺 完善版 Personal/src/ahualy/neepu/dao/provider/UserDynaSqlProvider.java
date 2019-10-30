package ahualy.neepu.dao.provider;

import static ahualy.neepu.util.common.Constants.USERTABLE;

import java.util.Map;


import org.apache.ibatis.jdbc.SQL;

import ahualy.neepu.pojo.User;

public class UserDynaSqlProvider {
	
	// 分页动态查询
		public String selectWhitParam(Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(USERTABLE);
					if(params.get("user") != null){
						User user = (User)params.get("user");
						if(user.getUsername() != null && !user.getUsername().equals("")){
							WHERE("  username LIKE CONCAT ('%',#{user.username},'%') ");
						}
						if(user.getStatus() != null && !user.getStatus().equals("")){
							WHERE(" status_id LIKE CONCAT ('%',#{user.status.id},'%') ");
						}
					}
					WHERE("id > 1");
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
					FROM(USERTABLE);
					if(params.get("user") != null){
						User user = (User)params.get("user");
						if(user.getUsername() != null && !user.getUsername().equals("")){
							WHERE(" username LIKE CONCAT ('%',#{user.username},'%') ");
						}
						if(user.getStatus() != null && !user.getStatus().equals("")){
							WHERE(" status_id LIKE CONCAT ('%',#{user.status.id},'%') ");
						}
					}
					WHERE("id > 1");
				}
			}.toString();
		}	
		
			// 动态插入
			public String insert_Notice(User job){
				return new SQL(){
					{
						INSERT_INTO(USERTABLE);
						if(job.getLoginname() != null ){
							VALUES("loginname", "#{loginname}");
						}
						if(job.getPassword()!=null){
							VALUES("password","#{password}");
						}
						if(job.getUsername()!=null){
							VALUES("username","#{username}");
						}
						if(job.getEmail()!=null){
							VALUES("email","#{email}");
						}
						
						if(job.getEmp_id()!=null) {
							VALUES("emp_id","#{emp_id}");
						}
						if(job.getChe_id()!=null) {
							VALUES("che_id","#{che_id}");
						}
						if(job.getCon_id()!=null) {
							VALUES("con_id","#{con_id}");
						}
						if(job.getSal_id()!=null) {
							VALUES("sal_id","#{sal_id}");
						}
						if(job.getCreatedate()!=null){
							VALUES("createdate","#{createdate}");
						}
					
						
					}
				}.toString();
			}	
			// 动态更新
			public String update_Notice(User user){
				
				return new SQL(){
					{
						UPDATE(USERTABLE);
						if(user.getLoginname() != null ){
							SET("loginname = #{loginname}");
						}
						if(user.getPassword()!=null){
							SET("password = #{password}");
						}
						if(user.getUsername()!=null){
							SET("username = #{username}");
						}
						if(user.getEmail()!=null){
							SET("email = #{email}");
						}
						if(user.getStatus()!=null){
							SET("status_id = #{status.id}");
						}
						WHERE(" id = #{id} ");
					}
				}.toString();
			}
}
