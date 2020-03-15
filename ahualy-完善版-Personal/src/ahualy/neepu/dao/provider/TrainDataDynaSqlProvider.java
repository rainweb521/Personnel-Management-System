package ahualy.neepu.dao.provider;



import org.apache.ibatis.jdbc.SQL;

import ahualy.neepu.pojo.TrainData;

import static ahualy.neepu.util.common.Constants.TRAINDATATABLE;

import java.util.Map;

public class TrainDataDynaSqlProvider {
	
	// 分页动态查询
		public String selectWhitParam(Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(TRAINDATATABLE);
					if(params.get("trainData") != null){
						TrainData trainData = (TrainData) params.get("trainData");
						if(trainData.getTitle() != null && !trainData.getTitle().equals("")){
							WHERE("  title LIKE CONCAT ('%',#{trainData.title},'%') ");
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
					FROM(TRAINDATATABLE);
					if(params.get("trainData") != null){
						TrainData trainData = (TrainData) params.get("trainData");
						if(trainData.getTitle() != null && !trainData.getTitle().equals("")){
							WHERE("  title LIKE CONCAT ('%',#{trainData.title},'%') ");
						}
					}
				}
			}.toString();
		}	
	
	// 动态插入
		public String insert(TrainData dept){
			
			return new SQL(){
				{
					INSERT_INTO(TRAINDATATABLE);
					if(dept.getTitle() != null ){
						VALUES("title", "#{title}");
					}
					if(dept.getRemark() != null ){
						VALUES("remark", "#{remark}");
					}
					if(dept.getUser()!= null ){
						VALUES("user_id", "#{user.id}");
					}
					if(dept.getFilename() != null ){
						VALUES("filename", "#{filename}");
					}
				}
			}.toString();
		}	
		// 动态更新
		public String update(TrainData dept){
			
			return new SQL(){
				{
					UPDATE(TRAINDATATABLE);
					if(dept.getTitle() != null){
						SET(" title = #{title} ");
					}
					if(dept.getRemark() != null){
						SET(" remark = #{remark} ");
					}
					if(dept.getFilename()!= null){
						SET(" filename = #{filename} ");
					}
					WHERE(" id = #{id} ");
				}
			}.toString();
		}
}
