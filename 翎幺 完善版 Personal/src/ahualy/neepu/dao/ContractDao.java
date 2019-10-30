package ahualy.neepu.dao;


import static ahualy.neepu.util.common.Constants.CONFIDENTIALITYCONTRACTTABLE;
import static ahualy.neepu.util.common.Constants.CONTRACTTABLE;
import static ahualy.neepu.util.common.Constants.DEPTTABLE;
import static ahualy.neepu.util.common.Constants.EMPLOYEETABLE;
import static ahualy.neepu.util.common.Constants.JOBTABLE;
import static ahualy.neepu.util.common.Constants.LABORCONTRACTTABLE;
import static ahualy.neepu.util.common.Constants.TRAINCONTRACTTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import ahualy.neepu.dao.provider.ContractDynaSqlProvider;
import ahualy.neepu.pojo.Contract;

public interface ContractDao {
	
	//查询
			@Select("select * from "+CONTRACTTABLE+" ")
			@Results({
				@Result(id=true,column="id",property="id"),
				@Result(column="CREATE_DATE",property="createdate"),
				@Result(column="TRAIN_CONTRACT",property="trainContract",
				one=@One(select="ahualy.neepu.dao.TraincontractDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="LABOR_CONTRACT",property="laborContract",
				one=@One(select="ahualy.neepu.dao.LaborcontractDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="CONFIDENTIALITY_CONTRACT",property="confidentialityContract",
				one=@One(select="ahualy.neepu.dao.ConfidentialitycontractDao.get_Info",
				fetchType=FetchType.EAGER)),
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
			List<Contract> get_List();
			@Select("select * from "+CONTRACTTABLE+"  where "
					+ "DEPT_ID =(select id from "+DEPTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or JOB_ID =(select id from "+JOBTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or EMP_ID =(select id from "+EMPLOYEETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or TRAIN_CONTRACT =(select id from "+TRAINCONTRACTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or LABOR_CONTRACT =(select id from "+LABORCONTRACTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or CONFIDENTIALITY_CONTRACT =(select id from "+CONFIDENTIALITYCONTRACTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or CREATE_DATE like binary CONCAT('%',#{content},'%')")
			
			@Results({
				@Result(id=true,column="id",property="id"),
				@Result(column="CREATE_DATE",property="createdate"),
				@Result(column="TRAIN_CONTRACT",property="trainContract",
				one=@One(select="ahualy.neepu.dao.TraincontractDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="LABOR_CONTRACT",property="laborContract",
				one=@One(select="ahualy.neepu.dao.LaborcontractDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="CONFIDENTIALITY_CONTRACT",property="confidentialityContract",
				one=@One(select="ahualy.neepu.dao.ConfidentialitycontractDao.get_Info",
				fetchType=FetchType.EAGER)),
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
			List<Contract> get_LikeList(String content);
			
			
			@SelectProvider(type=ContractDynaSqlProvider.class,method="insert_Contract")
			void insert_Info(Contract contract);
			
			@Select("select * from "+CONTRACTTABLE+" where id = #{id}")
			@Results({
				@Result(id=true,column="id",property="id"),
				@Result(column="CREATE_DATE",property="createdate"),
				@Result(column="TRAIN_CONTRACT",property="trainContract",
				one=@One(select="ahualy.neepu.dao.TraincontractDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="LABOR_CONTRACT",property="laborContract",
				one=@One(select="ahualy.neepu.dao.LaborcontractDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="CONFIDENTIALITY_CONTRACT",property="confidentialityContract",
				one=@One(select="ahualy.neepu.dao.ConfidentialitycontractDao.get_Info",
				fetchType=FetchType.EAGER)),
				
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
			Contract get_Info(Integer id);

			@SelectProvider(type=ContractDynaSqlProvider.class,method="update_Contract")
			void update_Info(Contract contract);
			// 根据id删除部门
			@Delete(" delete from "+CONTRACTTABLE+" where id = #{id} ")
			void delete_Info(Integer id);
			
			
			
			@SelectProvider(type=ContractDynaSqlProvider.class,method="selectWhitParam")
			@Results({
				@Result(id=true,column="id",property="id"),
				@Result(column="CREATE_DATE",property="createdate"),
				@Result(column="TRAIN_CONTRACT",property="trainContract",
				one=@One(select="ahualy.neepu.dao.TraincontractDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="LABOR_CONTRACT",property="laborContract",
				one=@One(select="ahualy.neepu.dao.LaborcontractDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="CONFIDENTIALITY_CONTRACT",property="confidentialityContract",
				one=@One(select="ahualy.neepu.dao.ConfidentialitycontractDao.get_Info",
				fetchType=FetchType.EAGER)),
				
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
			List<Contract> selectByPage(Map<String, Object> params);
			
			@SelectProvider(type=ContractDynaSqlProvider.class,method="count")
			public Integer count(Map<String, Object> params);

			
			@Select("select * from "+CONTRACTTABLE+" where emp_id = #{id}")
			Contract getEmp_id(Integer id);
			
			@Select("select count(*) from "+CONTRACTTABLE+"  where "
					+ "DEPT_ID =(select id from "+DEPTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or JOB_ID =(select id from "+JOBTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or EMP_ID =(select id from "+EMPLOYEETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or TRAIN_CONTRACT =(select id from "+TRAINCONTRACTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or LABOR_CONTRACT =(select id from "+LABORCONTRACTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or CONFIDENTIALITY_CONTRACT =(select id from "+CONFIDENTIALITYCONTRACTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or CREATE_DATE like binary CONCAT('%',#{content},'%')")
			public Integer countContract(String content);
			
			
			
}
