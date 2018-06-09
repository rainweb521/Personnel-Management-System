package com.rain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rain.dao.DeptDao;
import com.rain.dao.JobDao;
import com.rain.domain.Dept;
import com.rain.domain.Job;
import com.rain.service.RainService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("RainService")
public class RainServiceImpl implements RainService{

	@Autowired
	private DeptDao deptDao;
	@Autowired
	private JobDao jobDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findAllDept() {
		return deptDao.selectAllDept();
	}
	@Override
	public void addDept(Dept dept) {
		// TODO Auto-generated method stub
		deptDao.save(dept);
	}
	
	@Override
	public Dept get_Info(Integer id) {
		// TODO Auto-generated method stub
		Dept dept = deptDao.get_Info(id);
		return dept;
	}
	@Override
	public void update_Info(Dept dept) {
		// TODO Auto-generated method stub
		deptDao.update_Info(dept);
	}
	@Override
	public void delete_Info(Integer id) {
		// TODO Auto-generated method stub
		deptDao.delete_Info(id);
	}
	@Override
	public List<Dept> findAllDept(String content) {
		// TODO Auto-generated method stub
		System.out.println(content);
		return deptDao.selectLikeAllDept(content);
	}
	/**
	 * 职位管理的操作
	 */
	@Override
	public List<Job> findAllJob() {
		// TODO Auto-generated method stub
		return jobDao.get_List();
	}
	@Override
	public List<Job> findAllJob(String content) {
		// TODO Auto-generated method stub
		return jobDao.get_LikeList(content);
	}
	@Override
	public void addJob(Job job) {
		// TODO Auto-generated method stub
		jobDao.save(job);
	}
	
}
