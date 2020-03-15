package ahualy.neepu.dao;




import static ahualy.neepu.util.common.Constants.CHECKWORKTABLE;
import static ahualy.neepu.util.common.Constants.DEPTTABLE;
import static ahualy.neepu.util.common.Constants.EMPLOYEETABLE;
import static ahualy.neepu.util.common.Constants.JOBTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import ahualy.neepu.dao.provider.CheckworkDynaSqlProvider;
import ahualy.neepu.pojo.Checkwork;

public interface CheckworkDao {
	//查询
	@SelectProvider(type=CheckworkDynaSqlProvider.class,method="selectWhitParam")
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
	fetchType=FetchType.EAGER))
	})
	List<Checkwork> selectByPage(Map<String, Object> params);
	
	
	
	@Select("select * from "+CHECKWORKTABLE+"  where WORKINGDAYS like binary CONCAT('%',#{content},'%')"
			+ "or DAYSLEAVE like binary CONCAT('%',#{content},'%')"
			+ "or CREATEDATE like binary CONCAT('%',#{content},'%')"
			+ "or DEPT_ID =(select id from "+DEPTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or JOB_ID =(select id from "+JOBTABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or EMP_ID =(select id from "+EMPLOYEETABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or DAYSOUT like binary CONCAT('%',#{content},'%')")
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
	fetchType=FetchType.EAGER))
	})
	List<Checkwork> get_LikeList(String content);
	
	@Select("select count(*) from "+CHECKWORKTABLE+"  where WORKINGDAYS like binary CONCAT('%',#{content},'%')"
			+ "or DAYSLEAVE like binary CONCAT('%',#{content},'%')"
			+ "or CREATEDATE like binary CONCAT('%',#{content},'%')"
			+ "or DEPT_ID =(select id from "+DEPTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or JOB_ID =(select id from "+JOBTABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or EMP_ID =(select id from "+EMPLOYEETABLE+" where name like binary CONCAT('%',#{content},'%'))"
			+ "or DAYSOUT like binary CONCAT('%',#{content},'%')")
	public Integer countCheckwork(String content);
	
	
	
	
	@Select("select * from "+CHECKWORKTABLE+" where emp_id = #{id}")
	Checkwork getEmp_id(Integer id);
	
	
	@SelectProvider(type=CheckworkDynaSqlProvider.class,method="insert_Checkwork")
	void insert_Info(Checkwork checkwork);
	
	@Select("select * from "+CHECKWORKTABLE+" where id = #{id}")
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
	fetchType=FetchType.EAGER))
	})
	Checkwork get_Info(Integer id);

	@SelectProvider(type=CheckworkDynaSqlProvider.class,method="update_Checkwork")
	void update_Info(Checkwork checkwork);
	// 根据id删除部门
	@Delete(" delete from "+CHECKWORKTABLE+" where id = #{id} ")
	void delete_Info(Integer id);
	
	
	// 根据参数查询员工总数
    /**
     * Mybatis @SelectProvider 用自定义的provider类构造SQL语句
     * @param params
     * @return
     */
	@SelectProvider(type=CheckworkDynaSqlProvider.class,method="count")
	public Integer count(Map<String, Object> params);

}
