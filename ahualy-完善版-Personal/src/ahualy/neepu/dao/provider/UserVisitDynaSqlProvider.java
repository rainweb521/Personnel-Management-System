package ahualy.neepu.dao.provider;

import static ahualy.neepu.util.common.Constants.USERVISITTABLE;

import java.util.Map;


import org.apache.ibatis.jdbc.SQL;

import ahualy.neepu.pojo.UserVisit;

public class UserVisitDynaSqlProvider {
	
	// 分页动态查询
		public String selectWhitParam(Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(USERVISITTABLE);
					if(params.get("user") != null){
						UserVisit user = (UserVisit)params.get("user");
						if(user.getLoginname() != null && !user.getLoginname().equals("")){
							WHERE("  username LIKE CONCAT ('%',#{user.username},'%') ");
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
					FROM(USERVISITTABLE);
					if(params.get("user") != null){
						UserVisit user = (UserVisit)params.get("user");
						if(user.getLoginname() != null && !user.getLoginname().equals("")){
							WHERE(" username LIKE CONCAT ('%',#{user.username},'%') ");
						}
						
					}
				}
			}.toString();
		}	
		
			// 动态插入
			public String insert_Notice(UserVisit userVisit){
				
				return new SQL(){
					{
						INSERT_INTO(USERVISITTABLE);
						
						if(userVisit.getLogin_time()!= null ){
							VALUES("login_time", "#{login_time}");
						}
						if(userVisit.getExit_time()!= null ){
							VALUES("exit_time", "#{exit_time}");
						}
						if(userVisit.getUser_address()!= null ){
							VALUES("user_address", "#{user_address}");
						}
						if(userVisit.getBrowser()!=null) {
							VALUES("browser", "#{browser}");
						}
						if(userVisit.getVisit_ip()!=null) {
							VALUES("visit_ip", "#{visit_ip}");
						}
						if(userVisit.getUser_from()!=null) {
							VALUES("user_from", "#{user_from}");
						}
						
						if(userVisit.getSystem()!=null) {
							VALUES("system", "#{system}");
						}
						if(userVisit.getVersion()!=null) {
							VALUES("version", "#{version}");
						}
						if(userVisit.getLoginname()!=null) {
							VALUES("loginname", "#{loginname}");
						}
						if(userVisit.getIphone()!=null) {
							VALUES("iphone", "#{iphone}");
						}
					}
				}.toString();
			}	
			
}
