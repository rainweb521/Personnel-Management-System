package ahualy.neepu.dao;

import static ahualy.neepu.util.common.Constants.USERVISITTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import ahualy.neepu.dao.provider.UserVisitDynaSqlProvider;
import ahualy.neepu.pojo.UserVisit;

public interface UserVisitDao {

	@Select("select * from "+USERVISITTABLE+" ")
	List<UserVisit> get_List();
	
	
	@Select("select * from "+USERVISITTABLE+"  where "
			+ "login_time like CONCAT('%',#{content},'%')"
			+ "or exit_time like CONCAT('%',#{content},'%')"
			+ "or id like CONCAT('%',#{content},'%')"
			+ "or iphone like CONCAT('%',#{content},'%')"
			+ "or visit_ip like CONCAT('%',#{content},'%')"
			+ "or user_address like CONCAT('%',#{content},'%')"
			+ "or user_from like CONCAT('%',#{content},'%')"
			+ "or browser like CONCAT('%',#{content},'%')"
			+ "or system like CONCAT('%',#{content},'%')"
			+ "or version like CONCAT('%',#{content},'%')"
			+ "or loginname like CONCAT('%',#{content},'%')")
	List<UserVisit> get_LikeList(String content);
	
	@Select("select count(*) from "+USERVISITTABLE+"  where "
			+ "login_time like CONCAT('%',#{content},'%')"
			+ "or exit_time like CONCAT('%',#{content},'%')"
			+ "or id like CONCAT('%',#{content},'%')"
			+ "or iphone like CONCAT('%',#{content},'%')"
			+ "or visit_ip like CONCAT('%',#{content},'%')"
			+ "or user_address like CONCAT('%',#{content},'%')"
			+ "or user_from like CONCAT('%',#{content},'%')"
			+ "or browser like CONCAT('%',#{content},'%')"
			+ "or system like CONCAT('%',#{content},'%')"
			+ "or version like CONCAT('%',#{content},'%')"
			+ "or loginname like CONCAT('%',#{content},'%')")
	public Integer countUserVisit(String content);

	
	
	@SelectProvider(type=UserVisitDynaSqlProvider.class,method="insert_Notice")
	void insert_Info(UserVisit userVisit);
	
	
	@Delete(" delete from "+USERVISITTABLE+" where id = #{id} ")
	void delete_Info(Integer id);

	
	// 动态查询
	@SelectProvider(type=UserVisitDynaSqlProvider.class,method="selectWhitParam")
    List<UserVisit> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=UserVisitDynaSqlProvider.class,method="count")
	public Integer count(Map<String, Object> params);
}
