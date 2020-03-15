package ahualy.neepu.dao;

import static ahualy.neepu.util.common.Constants.TRAINDATATABLE;
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

import ahualy.neepu.dao.provider.TrainDataDynaSqlProvider;
import ahualy.neepu.pojo.TrainData;
public interface TrainDataDao {
	//查询
	@Select("select * from "+TRAINDATATABLE+" ")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="USER_ID",property="user",
			one=@One(select="ahualy.neepu.dao.UserDao.get_Info",
		fetchType=FetchType.EAGER))
	})
	List<TrainData> get_List();
	@Select("select * from "+TRAINDATATABLE+" where title like CONCAT('%',#{content},'%')"
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
	List<TrainData> get_LikeList(String content);
	
	@Select("select count(*) from "+TRAINDATATABLE+" where title like CONCAT('%',#{content},'%')"
			+ "or user_id =(select id from "+USERTABLE+" where username like CONCAT('%',#{content},'%'))"
			+ "or createdate like binary CONCAT('%',#{content},'%')"
			+ "or filename like CONCAT('%',#{content},'%')"
			+ "or remark like CONCAT('%',#{content},'%')")
	public Integer countTrainData(String content);
	
	
	@SelectProvider(type=TrainDataDynaSqlProvider.class,method="insert")
	void insert_Info(TrainData dept);
	
	@Select("select * from "+TRAINDATATABLE+" where id = #{id}")
	TrainData get_Info(Integer id);

	@SelectProvider(type=TrainDataDynaSqlProvider.class,method="update")
	void update_Info(TrainData dept);
	// 根据id删除部门
	@Delete(" delete from "+TRAINDATATABLE+" where id = #{id} ")
	void delete_Info(Integer id);

	
	// 动态查询
		@SelectProvider(type=TrainDataDynaSqlProvider.class,method="selectWhitParam")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="USER_ID",property="user",
				one=@One(select="ahualy.neepu.dao.UserDao.get_Info",
			fetchType=FetchType.EAGER))
		})
	List<TrainData> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=TrainDataDynaSqlProvider.class,method="count")
	public Integer count(Map<String, Object> params);
}
