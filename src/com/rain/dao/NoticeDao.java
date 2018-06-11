package com.rain.dao;

import static com.rain.util.common.Constants.NOTICETABLE;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.rain.dao.provider.NoticeDynaSqlProvider;
import com.rain.domain.Notice;

public interface NoticeDao {

	@Select("select * from "+NOTICETABLE+" ")
	List<Notice> get_List();
	@Select("select * from "+NOTICETABLE+"  where title like CONCAT('%',#{content},'%')")
	List<Notice> get_LikeList(String content);

	@SelectProvider(type=NoticeDynaSqlProvider.class,method="insert_Notice")
	void insert_Info(Notice employee);
	
	@Select("select * from "+NOTICETABLE+" where id = #{id}")
	Notice get_Info(Integer id);

	@SelectProvider(type=NoticeDynaSqlProvider.class,method="update_Notice")
	void update_Info(Notice employee);
	// 根据id删除部门
	@Delete(" delete from "+NOTICETABLE+" where id = #{id} ")
	void delete_Info(Integer id);

}
