package com.rain.dao;

import static com.rain.util.common.Constants.DEPTTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.rain.domain.Dept;
public interface DeptDao {
	//查询
	@Select("select * from "+DEPTTABLE+" ")
	List<Dept> selectAllDept();
}
