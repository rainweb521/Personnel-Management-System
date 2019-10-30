package ahualy.neepu.dao;

import static ahualy.neepu.util.common.Constants.DOCUMENTTABLE;
import static ahualy.neepu.util.common.Constants.USERTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import ahualy.neepu.dao.provider.DocumentDynaSqlProvider;
import ahualy.neepu.pojo.Document;
public interface DocumentDao {
	//查询
	@Select("select * from "+DOCUMENTTABLE+" ")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="USER_ID",property="user",
			one=@One(select="ahualy.neepu.dao.UserDao.get_Info",
		fetchType=FetchType.EAGER))
	})
	List<Document> get_List();
	@Select("select * from "+DOCUMENTTABLE+" where title like CONCAT('%',#{content},'%')"
			+ "or user_id =(select id from "+USERTABLE+" where username like CONCAT('%',#{content},'%'))"
			+ "or createdate like binary CONCAT('%',#{content},'%')"
			+ "or filename like CONCAT('%',#{content},'%')"
			+ "or remark like CONCAT('%',#{content},'%')")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="USER_ID",property="user",
			one=@One(select="ahualy.neepu.dao.UserDao.get_Info",
		fetchType=FetchType.EAGER))
	})
	List<Document> get_LikeList(String content);
	
	@Select("select count(*) from "+DOCUMENTTABLE+" where title like CONCAT('%',#{content},'%')"
			+ "or user_id =(select id from "+USERTABLE+" where username like CONCAT('%',#{content},'%'))"
			+ "or createdate like binary CONCAT('%',#{content},'%')"
			+ "or filename like CONCAT('%',#{content},'%')"
			+ "or remark like CONCAT('%',#{content},'%')")
	public Integer countDocument(String content);
	
	
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="insert")
	void insert_Info(Document dept);
	
	@Select("select * from "+DOCUMENTTABLE+" where id = #{id}")
	Document get_Info(Integer id);

	@SelectProvider(type=DocumentDynaSqlProvider.class,method="update")
	void update_Info(Document dept);
	// 根据id删除部门
	@Delete(" delete from "+DOCUMENTTABLE+" where id = #{id} ")
	void delete_Info(Integer id);

	
	// 动态查询
		@SelectProvider(type=DocumentDynaSqlProvider.class,method="selectWhitParam")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="USER_ID",property="user",
				one=@One(select="ahualy.neepu.dao.UserDao.get_Info",
			fetchType=FetchType.EAGER))
		})
	List<Document> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="count")
	public Integer count(Map<String, Object> params);
}
