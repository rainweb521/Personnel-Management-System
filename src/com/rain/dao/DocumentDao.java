package com.rain.dao;

import static com.rain.util.common.Constants.DOCUMENTTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import com.rain.dao.provider.DocumentDynaSqlProvider;
import com.rain.domain.Document;
public interface DocumentDao {
	//查询
	@Select("select * from "+DOCUMENTTABLE+" ")
	List<Document> get_List();
	@Select("select * from "+DOCUMENTTABLE+" where title like CONCAT('%',#{content},'%')")
	List<Document> get_LikeList(String content);
	
	
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="insert")
	void insert_Info(Document dept);
	
	@Select("select * from "+DOCUMENTTABLE+" where id = #{id}")
	Document get_Info(Integer id);

	@SelectProvider(type=DocumentDynaSqlProvider.class,method="update")
	void update_Info(Document dept);
	// 根据id删除部门
	@Delete(" delete from "+DOCUMENTTABLE+" where id = #{id} ")
	void delete_Info(Integer id);

	
}
