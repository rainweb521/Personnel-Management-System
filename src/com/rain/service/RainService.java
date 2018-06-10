package com.rain.service;

import java.util.List;

import com.rain.domain.Dept;
import com.rain.domain.Employee;
import com.rain.domain.Job;

public interface RainService {
	/**
	 * 部门信息的service
	 * @param content
	 * @return
	 */
	List<Dept> findAllDept(String content);
	
	void addDept(Dept dept);

	Dept get_Info(Integer id);

	void update_Info(Dept dept);

	void delete_Info(Integer id);

	List<Dept> findAllDept();
	/**
	 * 职位信息的service
	 * @return
	 */

	List<Job> findAllJob();
	
	List<Job> findAllJob(String content);

	Job get_JobInfo(Integer id);

	void update_JobInfo(Job job);

	void insert_JobInfo(Job job);

	void delete_JobInfo(Integer id);
	/**
	 * 员工信息的service
	 */
	List<Employee> get_EmployeeList();
	List<Employee> get_EmployeeLikeList(String content);
	Employee get_EmployeeInfo(Integer id);
	void update_EmployeeInfo(Employee data);
	void insert_EmployeeInfo(Employee data);
	void delete_EmployeeInfo(Integer id);
	
}
