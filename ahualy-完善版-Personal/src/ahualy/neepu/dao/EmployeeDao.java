package ahualy.neepu.dao;

import static ahualy.neepu.util.common.Constants.DEPTTABLE;
import static ahualy.neepu.util.common.Constants.EDUCATIONTABLE;
import static ahualy.neepu.util.common.Constants.EMPLOYEETABLE;
import static ahualy.neepu.util.common.Constants.JOBTABLE;
import static ahualy.neepu.util.common.Constants.SEXTABLE;
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

import ahualy.neepu.dao.provider.EmployeeDynaSqlProvider;
import ahualy.neepu.pojo.Employee;

public interface EmployeeDao {
	/**
	 * 
	 * @return
	 */
	//查询
			@Select("select * from "+EMPLOYEETABLE+" ")
			@Results({
				
				@Result(column="DEPT_ID",property="dept",
				one=@One(select="ahualy.neepu.dao.DeptDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="sex_id",property="sex",
				one=@One(select="ahualy.neepu.dao.SexDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="education_id",property="education",
				one=@One(select="ahualy.neepu.dao.EducationDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="JOB_ID",property="job",
				one=@One(select="ahualy.neepu.dao.JobDao.get_Info",
			    fetchType=FetchType.EAGER))
				//通过员工表的id去查询user表
				
				
				//FetchType.LAZY：懒加载，加载一个实体时，定义懒加载的属性不会马上从数据库中加载

				//FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载
			})
			List<Employee> get_List();
			
			
			@Select("select * from "+EMPLOYEETABLE+"  where name like binary CONCAT('%',#{content},'%')"
					+ "or user_id =(select emp_id from "+USERTABLE+" where loginname =#{content})"
					+ "or DEPT_ID =(select id from "+DEPTTABLE+" where name like CONCAT('%',#{content},'%'))"
					+ "or JOB_ID =(select id from "+JOBTABLE+" where name like CONCAT('%',#{content},'%'))"
					+ "or sex_id =(select id from "+SEXTABLE+" where name like CONCAT('%',#{content},'%'))"
					+ "or education_id =(select id from "+EDUCATIONTABLE+" where name like CONCAT('%',#{content},'%'))"
					+ "or address like binary CONCAT('%',#{content},'%')"
					+ "or card_id like binary CONCAT('%',#{content},'%')"
					+ "or phone like binary CONCAT('%',#{content},'%')"
					+ "or email like binary CONCAT('%',#{content},'%')"
					+ "or createdate like binary CONCAT('%',#{content},'%')")
			@Results({
				@Result(column="user_id",property="user",
				one=@One(select="ahualy.neepu.dao.UserDao.get_StaticInfoLoginname",
				fetchType=FetchType.EAGER)),
				@Result(column="DEPT_ID",property="dept",
				one=@One(select="ahualy.neepu.dao.DeptDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="sex_id",property="sex",
				one=@One(select="ahualy.neepu.dao.SexDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="education_id",property="education",
				one=@One(select="ahualy.neepu.dao.EducationDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="JOB_ID",property="job",
				one=@One(select="ahualy.neepu.dao.JobDao.get_Info",
			fetchType=FetchType.EAGER))
			})
			List<Employee> get_LikeList(String content);
			
			@Select("select id from "+EMPLOYEETABLE+" where name = #{employee_name}")
			Integer getIdByName(String employee_name);
			
			
			@SelectProvider(type=EmployeeDynaSqlProvider.class,method="insert_Employee")
			void insert_Info(Employee employee);
			
			@Select("select * from "+EMPLOYEETABLE+" where id = #{id}")
			@Results({
				@Result(column="DEPT_ID",property="dept",
				one=@One(select="ahualy.neepu.dao.DeptDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="sex_id",property="sex",
				one=@One(select="ahualy.neepu.dao.SexDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="education_id",property="education",
				one=@One(select="ahualy.neepu.dao.EducationDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="JOB_ID",property="job",
				one=@One(select="ahualy.neepu.dao.JobDao.get_Info",
			fetchType=FetchType.EAGER))
			})
			Employee get_Info(Integer id);

			@SelectProvider(type=EmployeeDynaSqlProvider.class,method="update_Employee")
			void update_Info(Employee employee);
			// 根据id删除部门
			@Delete(" delete from "+EMPLOYEETABLE+" where id = #{id} ")
			void delete_Info(Integer id);
			
			
			@SelectProvider(type=EmployeeDynaSqlProvider.class,method="selectWhitParam")
			@Results({
				@Result(column="user_id",property="user",
				one=@One(select="ahualy.neepu.dao.UserDao.get_StaticInfoLoginname",
				fetchType=FetchType.EAGER)),
				@Result(column="DEPT_ID",property="dept",
				one=@One(select="ahualy.neepu.dao.DeptDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="sex_id",property="sex",
				one=@One(select="ahualy.neepu.dao.SexDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="education_id",property="education",
				one=@One(select="ahualy.neepu.dao.EducationDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="JOB_ID",property="job",
				one=@One(select="ahualy.neepu.dao.JobDao.get_Info",
			fetchType=FetchType.EAGER))
			})
			List<Employee> selectByPage(Map<String, Object> params);
			
			
			
			
			
			
			
			@SelectProvider(type=EmployeeDynaSqlProvider.class,method="count")
			public Integer count(Map<String, Object> params);
			
			@Select("select count(*) from "+EMPLOYEETABLE+"  where name like binary CONCAT('%',#{content},'%')"
					+ "or user_id =(select emp_id from "+USERTABLE+" where loginname =#{content})"
					+ "or DEPT_ID =(select id from "+DEPTTABLE+" where name like CONCAT('%',#{content},'%'))"
					+ "or JOB_ID =(select id from "+JOBTABLE+" where name like CONCAT('%',#{content},'%'))"
					+ "or sex_id =(select id from "+SEXTABLE+" where name like CONCAT('%',#{content},'%'))"
					+ "or education_id =(select id from "+EDUCATIONTABLE+" where name like CONCAT('%',#{content},'%'))"
					+ "or address like binary CONCAT('%',#{content},'%')"
					+ "or card_id like binary CONCAT('%',#{content},'%')"
					+ "or phone like binary CONCAT('%',#{content},'%')"
					+ "or email like binary CONCAT('%',#{content},'%')"
					+ "or createdate like binary CONCAT('%',#{content},'%')")
			public Integer countEmployee(String content);
			
			
			@Select("select con_id from "+EMPLOYEETABLE+" order by id DESC limit 1 ")
			Integer togetCon_id();
			@Select("select sal_id from "+EMPLOYEETABLE+" order by id DESC limit 1 ")
			Integer togetSal_id();
			@Select("select che_id from "+EMPLOYEETABLE+" order by id DESC limit 1 ")
			Integer togetChe_id();
			
			//让结果只显示省份即可
			@Select("select SUBSTRING(address,1,2) from "+EMPLOYEETABLE+" ")
			List<String> get_addressList();
			
			
}
