package ahualy.neepu.dao;

import static ahualy.neepu.util.common.Constants.NOTICETABLE;
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

import ahualy.neepu.dao.provider.NoticeDynaSqlProvider;
import ahualy.neepu.pojo.Notice;

public interface NoticeDao {

	// 动态查询
	    @Select("select id, title, content, createdate, user_id from "+NOTICETABLE+" ")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="USER_ID",property="user",
				one=@One(select="ahualy.neepu.dao.UserDao.get_Info",
			fetchType=FetchType.EAGER))
		})
	List<Notice> get_List();
	
	@Select("select * from "+NOTICETABLE+"  where title like CONCAT('%',#{content},'%')"
			+ "or user_id =(select id from "+USERTABLE+" where username like CONCAT('%',#{content},'%'))"
			+ "or createdate like binary CONCAT('%',#{content},'%')"
			+ "or content like CONCAT('%',#{content},'%')")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="USER_ID",property="user",
			one=@One(select="ahualy.neepu.dao.UserDao.get_Info",
		fetchType=FetchType.EAGER))
	})
	List<Notice> get_LikeList(String content);
	
	@Select("select count(*) from "+NOTICETABLE+"  where title like CONCAT('%',#{content},'%')"
			+ "or user_id =(select id from "+USERTABLE+" where username like CONCAT('%',#{content},'%'))"
			+ "or createdate like binary CONCAT('%',#{content},'%')"
			+ "or content like CONCAT('%',#{content},'%')")
	public Integer countNotice(String content);

	@SelectProvider(type=NoticeDynaSqlProvider.class,method="insert_Notice")
	void insert_Info(Notice employee);
	
	@Select("select * from "+NOTICETABLE+" where id = #{id}")
	Notice get_Info(Integer id);

	@SelectProvider(type=NoticeDynaSqlProvider.class,method="update_Notice")
	void update_Info(Notice employee);
	// 根据id删除公告
	@Delete(" delete from "+NOTICETABLE+" where id = #{id} ")
	void delete_Info(Integer id);

	@SelectProvider(type=NoticeDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="USER_ID",property="user",
			one=@One(select="ahualy.neepu.dao.UserDao.get_Info",
		fetchType=FetchType.EAGER))
	})
     List<Notice> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="count")
	public Integer count(Map<String, Object> params);
}
