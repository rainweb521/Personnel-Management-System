package com.rain.dao;

import static com.rain.util.common.Constants.JOBTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.rain.domain.Dept;
import com.rain.domain.Job;

public interface JobDao {
	//查询
		@Select("select * from "+JOBTABLE+" ")
		List<Job> get_List();
		@Select("select * from "+JOBTABLE+"  where name like CONCAT('%',#{content},'%')")
		List<Job> get_LikeList(String content);
		
		void save(Job job);
}
