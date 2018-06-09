package com.rain.service;

import java.util.List;

import com.rain.domain.Dept;
import com.rain.domain.Job;

public interface RainService {
	List<Dept> findAllDept(String content);
	
	void addDept(Dept dept);

	Dept get_Info(Integer id);

	void update_Info(Dept dept);

	void delete_Info(Integer id);

	List<Dept> findAllDept();

	List<Job> findAllJob();
	
	List<Job> findAllJob(String content);

	void addJob(Job job);
}
