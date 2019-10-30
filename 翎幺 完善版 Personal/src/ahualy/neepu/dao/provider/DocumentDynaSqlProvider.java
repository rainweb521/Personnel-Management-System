package ahualy.neepu.dao.provider;



import org.apache.ibatis.jdbc.SQL;

import ahualy.neepu.pojo.Document;

import static ahualy.neepu.util.common.Constants.DOCUMENTTABLE;

import java.util.Map;

public class DocumentDynaSqlProvider {
	
	// 分页动态查询
		public String selectWhitParam(Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(DOCUMENTTABLE);
					if(params.get("document") != null){
						Document document = (Document) params.get("document");
						if(document.getTitle() != null && !document.getTitle().equals("")){
							WHERE("  title LIKE CONCAT ('%',#{document.title},'%') ");
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
					FROM(DOCUMENTTABLE);
					if(params.get("document") != null){
						Document document = (Document) params.get("document");
						if(document.getTitle() != null && !document.getTitle().equals("")){
							WHERE("  title LIKE CONCAT ('%',#{document.title},'%') ");
						}
					}
				}
			}.toString();
		}	
	
	// 动态插入
		public String insert(Document dept){
			
			return new SQL(){
				{
					INSERT_INTO(DOCUMENTTABLE);
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
		public String update(Document dept){
			
			return new SQL(){
				{
					UPDATE(DOCUMENTTABLE);
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
