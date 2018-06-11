package com.rain.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import static com.rain.util.common.Constants.DOCUMENTTABLE;

import com.rain.domain.Document;

public class DocumentDynaSqlProvider {
	
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
					if(dept.getCreate_date() != null ){
						VALUES("create_date", "#{create_date}");
					}
					if(dept.getUser_id() != null ){
						VALUES("user_id", "#{user_id}");
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
					WHERE(" id = #{id} ");
				}
			}.toString();
		}
}
