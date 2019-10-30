package ahualy.neepu.dao;

import static ahualy.neepu.util.common.Constants.DEPTTABLE;
import static ahualy.neepu.util.common.Constants.EMPLOYEETABLE;
import static ahualy.neepu.util.common.Constants.JOBTABLE;
import static ahualy.neepu.util.common.Constants.LEAVETABLE;
import static ahualy.neepu.util.common.Constants.LEAVESTATUSTABLE;
import static ahualy.neepu.util.common.Constants.LEAVETYPETABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import ahualy.neepu.dao.provider.LeaveDynaSqlProvider;
import ahualy.neepu.pojo.Leave;

public interface LeaveDao {
	
	//查询
			@Select("select * from "+LEAVETABLE+" ")
			List<Leave> selectAllLeave();
			
			@Select("select emp_id from "+LEAVETABLE+" where id = #{id}")
			Integer selectEmpId(Integer id);
			
			@Select("select * from "+LEAVETABLE+" where name like CONCAT('%',#{content},'%')")
			List<Leave> selectLikeAllLeave(String content);

			@Select("select * from "+LEAVETABLE+" where emp_id = #{id}")
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
				@Result(column="leavetype",property="leavetype",
				one=@One(select="ahualy.neepu.dao.LeaveTypeDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="leavestatus",property="leavestatus",
				one=@One(select="ahualy.neepu.dao.LeaveStatusDao.get_Info",
				fetchType=FetchType.EAGER))
			})
			List<Leave> get_Info(Integer id);
			
			
			
			
			@SelectProvider(type=LeaveDynaSqlProvider.class,method="insert_Leave")
			void insert_Info(Leave leave);
			
			
			
			@Delete(" delete from "+LEAVETABLE+" where id = #{id} ")
			void delete_Info(Integer id);
			
			
			@Select("select id,startdata,enddata,leavedays,leavetype,leavestatus,content from "+LEAVETABLE+" where id = #{id}")
			@Results({
				@Result(id=true,column="id",property="id"),
				@Result(column="leavetype",property="leavetype",
				one=@One(select="ahualy.neepu.dao.LeaveTypeDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="leavestatus",property="leavestatus",
				one=@One(select="ahualy.neepu.dao.LeaveStatusDao.get_Info",
				fetchType=FetchType.EAGER))
			})
			Leave selectLeave(Integer id);
			
			@SelectProvider(type=LeaveDynaSqlProvider.class,method="update_Leave")
			void update_Info(Leave leave);
			
			@Select("select count(*) from "+LEAVETABLE+" where emp_id = #{id}")
			Integer get_Count(Integer id);
			
			
			
			//管理员调用的方法
			@SelectProvider(type=LeaveDynaSqlProvider.class,method="count")
			Integer count(Map<String, Object> params);
			
			
			@SelectProvider(type=LeaveDynaSqlProvider.class,method="selectWhitParam")
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
				@Result(column="leavetype",property="leavetype",
				one=@One(select="ahualy.neepu.dao.LeaveTypeDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="leavestatus",property="leavestatus",
				one=@One(select="ahualy.neepu.dao.LeaveStatusDao.get_Info",
				fetchType=FetchType.EAGER))
			})
			List<Leave> selectByPage(Map<String, Object> params);
			
			
			//按照关键字content查询请假信息
			@Select("select * from "+LEAVETABLE+"  where leavedays like binary CONCAT('%',#{content},'%')"
					+ "or emp_id =(select id from "+EMPLOYEETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or dept_id =(select id from "+DEPTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or job_id =(select id from "+JOBTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or leavestatus =(select id from "+LEAVESTATUSTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or leavetype =(select id from "+LEAVETYPETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or content like binary CONCAT('%',#{content},'%')"
					+ "or leavetime like binary CONCAT('%',#{content},'%')")
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
				@Result(column="leavetype",property="leavetype",
				one=@One(select="ahualy.neepu.dao.LeaveTypeDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="leavestatus",property="leavestatus",
				one=@One(select="ahualy.neepu.dao.LeaveStatusDao.get_Info",
				fetchType=FetchType.EAGER))
			})
			List<Leave> get_LikeList(String content);
			
			
			@Select("select count(*) from "+LEAVETABLE+"  where leavedays like binary CONCAT('%',#{content},'%')"
					+ "or emp_id =(select id from "+EMPLOYEETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or dept_id =(select id from "+DEPTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or job_id =(select id from "+JOBTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or leavestatus =(select id from "+LEAVESTATUSTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or leavetype =(select id from "+LEAVETYPETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or content like binary CONCAT('%',#{content},'%')"
					+ "or leavetime like binary CONCAT('%',#{content},'%')")
			Integer countLeave(String content);

}
