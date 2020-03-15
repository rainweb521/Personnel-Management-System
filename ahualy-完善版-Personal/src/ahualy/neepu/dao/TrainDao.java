package ahualy.neepu.dao;

import static ahualy.neepu.util.common.Constants.DEPTTABLE;
import static ahualy.neepu.util.common.Constants.EMPLOYEETABLE;
import static ahualy.neepu.util.common.Constants.JOBTABLE;
import static ahualy.neepu.util.common.Constants.COMPLETIONTABLE;
import static ahualy.neepu.util.common.Constants.TRAINTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import ahualy.neepu.dao.provider.TrainDynaSqlProvider;
import ahualy.neepu.pojo.Train;

public interface TrainDao {
	
	
	//查询
	    @Select("select * from "+TRAINTABLE+" where id = #{id}")
	    @Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="EMP_ID",property="employee",
			one=@One(select="ahualy.neepu.dao.EmployeeDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="completion",property="completion",
			one=@One(select="ahualy.neepu.dao.CompletionDao.get_Info",
			fetchType=FetchType.EAGER))
		})
	    Train selectTrainById(Integer id);
	
		@SelectProvider(type=TrainDynaSqlProvider.class,method="selectWhitParam")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="DEPT_ID",property="dept",
			one=@One(select="ahualy.neepu.dao.DeptDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="EMP_ID",property="employee",
			one=@One(select="ahualy.neepu.dao.EmployeeDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="JOB_ID",property="job",
			one=@One(select="ahualy.neepu.dao.JobDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="completion",property="completion",
			one=@One(select="ahualy.neepu.dao.CompletionDao.get_Info",
			fetchType=FetchType.EAGER))
		})
		List<Train> selectByPage(Map<String, Object> params);
		
		@Select("select * from "+TRAINTABLE+" where emp_id = #{id}")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="dept_id",property="dept",
			one=@One(select="ahualy.neepu.dao.DeptDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="emp_id",property="employee",
			one=@One(select="ahualy.neepu.dao.EmployeeDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="job_id",property="job",
			one=@One(select="ahualy.neepu.dao.JobDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="completion",property="completion",
			one=@One(select="ahualy.neepu.dao.CompletionDao.get_Info",
			fetchType=FetchType.EAGER))
		})
		List<Train> get_Info(Integer id);
		
		@SelectProvider(type=TrainDynaSqlProvider.class,method="count")
		public Integer count(Map<String, Object> params);
		
		@Select("select count(*) from "+TRAINTABLE+" where emp_id = #{id}")
		Integer get_Count(Integer id);
	
	//添加
	@SelectProvider(type=TrainDynaSqlProvider.class,method="insert_Train")
	void insert_Info(Train train);
	
	//更新
	@SelectProvider(type=TrainDynaSqlProvider.class,method="update_Train")
	void update_Info(Train train);
	
	//删除
	@Delete(" delete from "+TRAINTABLE+" where id = #{id} ")
	void delete_Info(Integer id);
	
	//按照关键字content查询请假信息
	@Select("select * from "+TRAINTABLE+"  where content like binary CONCAT('%',#{content},'%')"
			+ "or emp_id =(select id from "+EMPLOYEETABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or dept_id =(select id from "+DEPTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or job_id =(select id from "+JOBTABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or completion =(select id from "+COMPLETIONTABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or startdata like binary CONCAT('%',#{content},'%')"
			+ "or enddata like binary CONCAT('%',#{content},'%')"
			+ "or totallength like binary CONCAT('%',#{content},'%')"
			+ "or grade like binary CONCAT('%',#{content},'%')"
			+ "or traintime like binary CONCAT('%',#{content},'%')")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="dept_id",property="dept",
		one=@One(select="ahualy.neepu.dao.DeptDao.get_Info",
		fetchType=FetchType.EAGER)),
		@Result(column="emp_id",property="employee",
		one=@One(select="ahualy.neepu.dao.EmployeeDao.get_Info",
		fetchType=FetchType.EAGER)),
		@Result(column="job_id",property="job",
		one=@One(select="ahualy.neepu.dao.JobDao.get_Info",
		fetchType=FetchType.EAGER)),
		@Result(column="completion",property="completion",
		one=@One(select="ahualy.neepu.dao.CompletionDao.get_Info",
		fetchType=FetchType.EAGER))
	})
	List<Train> get_LikeList(String content);
	
	
	@Select("select count(*) from "+TRAINTABLE+"  where content like binary CONCAT('%',#{content},'%')"
			+ "or emp_id =(select id from "+EMPLOYEETABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or dept_id =(select id from "+DEPTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or job_id =(select id from "+JOBTABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or completion =(select id from "+COMPLETIONTABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or startdata like binary CONCAT('%',#{content},'%')"
			+ "or enddata like binary CONCAT('%',#{content},'%')"
			+ "or totallength like binary CONCAT('%',#{content},'%')"
			+ "or grade like binary CONCAT('%',#{content},'%')"
			+ "or traintime like binary CONCAT('%',#{content},'%')")
	Integer countTrain(String content);
		

}
