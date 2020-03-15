package ahualy.neepu.dao.provider;


import static ahualy.neepu.util.common.Constants.TRAINTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import ahualy.neepu.pojo.Train;
public class TrainDynaSqlProvider {

public String insert_Train(Train train){
		
		return new SQL(){
			{
				INSERT_INTO(TRAINTABLE);
				if(train.getDept() != null){
					VALUES(" DEPT_ID" , "#{dept.id}");
				}
				if(train.getJob()!= null){
					VALUES(" JOB_ID" , "#{job.id}");
				}
				if(train.getEmployee() != null){
					VALUES(" EMP_ID" , "#{employee.id}");
				}
				if(train.getContent() != null){
					VALUES(" content" , "#{content}");
				}
				if(train.getStartdata() != null){
					VALUES(" startdata" , "#{startdata}");
				}
				if(train.getEnddata() != null){
					VALUES(" enddata" , "#{enddata}");
				}
				if(train.getTotallength() != null){
					VALUES(" totallength" , "#{totallength}");
				}
				if(train.getCompletion() != null){
					VALUES(" completion" , "#{completion.id}");
				}
				if(train.getGrade() != null){
					VALUES(" grade" , "#{grade}");
				}
			}
		}.toString();
	}	



	//分页动态查询
	public String selectWhitParam(Map<String, Object> params){
	String sql =  new SQL(){
		{
			SELECT("*");
			FROM(TRAINTABLE);
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
				FROM(TRAINTABLE);
			}
		}.toString();
	}	
	
	
	
	// 动态更新
	public String update_Train(Train train){
		
		return new SQL(){
			{
				UPDATE(TRAINTABLE);
				if(train.getContent()!= null){
					SET("content = #{content}");
				}
				if(train.getStartdata()!= null){
					SET("startdata = #{startdata}");
				}
				if(train.getEnddata()!= null){
					SET("enddata = #{enddata}");
				}
				if(train.getTotallength()!= null){
					SET("totallength = #{totallength}");
				}
				if(train.getCompletion()!= null){
					SET("completion = #{completion.id}");
				}
				if(train.getGrade()!= null){
					SET("grade = #{grade}");
				}
				WHERE("id = #{id}");
			}
		}.toString();
	}
}
