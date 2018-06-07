package com.rain.service;

import java.util.List;

import com.rain.domain.Dept;

public interface RainService {
	List<Dept> findAllDept();
	
	void addDept(Dept dept);

	Dept get_Info(Integer id);

	void update_Info(Dept dept);

	void delete_Info(Integer id);
}
