package ahualy.neepu.dao.provider;

import static ahualy.neepu.util.common.Constants.NOTICETABLE;

import java.util.Map;


import org.apache.ibatis.jdbc.SQL;

import ahualy.neepu.pojo.Notice;

public class NoticeDynaSqlProvider {
	
	
	// 分页动态查询
		public String selectWhitParam(Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(NOTICETABLE);
					if(params.get("notice") != null){
						Notice notice = (Notice)params.get("notice");
						if(notice.getTitle() != null && !notice.getTitle().equals("")){
							WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
						}
						if(notice.getContent() != null && !notice.getContent().equals("")){
							WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
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
					FROM(NOTICETABLE);
					if(params.get("notice") != null){
						Notice notice = (Notice)params.get("notice");
						if(notice.getTitle() != null && !notice.getTitle().equals("")){
							WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
						}
						if(notice.getContent() != null && !notice.getContent().equals("")){
							WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
						}
					}
				}
			}.toString();
		}	
	
			// 动态插入
			public String insert_Notice(Notice notice){
				
				return new SQL(){
					{
						INSERT_INTO(NOTICETABLE);
						if(notice.getTitle() != null ){
							VALUES("title", "#{title}");
						}
						if(notice.getContent()!=null){
							VALUES("content","#{content}");
						}
						if(notice.getUser() != null && notice.getUser().getId() != null){
							VALUES("user_id", "#{user.id}");
						}
						
					}
				}.toString();
			}	
			// 动态更新
			public String update_Notice(Notice notice){
				
				return new SQL(){
					{
						UPDATE(NOTICETABLE);
						if(notice.getTitle() != null ){
							SET("title = #{title}");
						}
						if(notice.getContent()!=null){
							SET("content = #{content}");
						}
						if(notice.getCreatedate()!=null){
							SET("createdate = #{createdate}");
						}
						WHERE(" id = #{id} ");
					}
				}.toString();
			}
}
