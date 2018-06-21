package com.rain.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rain.dao.DeptDao;
import com.rain.dao.EmployeeDao;
import com.rain.dao.JobDao;
import com.rain.dao.NoticeDao;
import com.rain.dao.UserDao;
import com.rain.dao.DocumentDao;
import com.rain.domain.Dept;
import com.rain.domain.Document;
import com.rain.domain.Employee;
import com.rain.domain.Job;
import com.rain.domain.Notice;
import com.rain.domain.User;
import com.rain.service.RainService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("RainService")
public class RainServiceImpl implements RainService{

	@Autowired
	private DeptDao deptDao;
	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployeeDao employeedao;
	@Autowired
	private NoticeDao noticedao;
	@Autowired
	private DocumentDao documentdao;
	@Autowired
	private UserDao userdao;

	/**
	 * 部门信息的管理
	 */
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
	public Job get_JobInfo(Integer id) {
		// TODO Auto-generated method stub
		return jobDao.get_Info(id);
	}
	@Override
	public void update_JobInfo(Job job) {
		// TODO Auto-generated method stub
		jobDao.update_Info(job);
	}
	@Override
	public void insert_JobInfo(Job job) {
		// TODO Auto-generated method stub
		jobDao.insert_Info(job);
	}
	@Override
	public void delete_JobInfo(Integer id) {
		// TODO Auto-generated method stub
		jobDao.delete_Info(id);
	}
	/**
	 * 员工信息的管理
	 */
	@Override
	public List<Employee> get_EmployeeList() {
		// TODO Auto-generated method stub
		/**
		 * 将部门，职位id中的信息提取出来
		 */
		List<Employee> list = employeedao.get_List();
		int size = list.size();
		List<Employee> list2 = new ArrayList<>();
		for(int i = 0;i<size;i++){
			Employee data = list.get(i);
			Dept dept = deptDao.get_Info(data.getDept_id());
			data.setDept(dept);
			Job job = jobDao.get_Info(data.getJob_id());
			data.setJob(job);
			list2.add(i,data);
		}
		return list2;
	}
	@Override
	public List<Employee> get_EmployeeLikeList(String content) {
		// TODO Auto-generated method stub
		/**
		 * 将部门，职位id中的信息提取出来
		 */
		List<Employee> list = employeedao.get_LikeList(content);
		int size = list.size();
		List<Employee> list2 = new ArrayList<>();
		for(int i = 0;i<size;i++){
			Employee data = list.get(i);
			Dept dept = deptDao.get_Info(data.getDept_id());
			data.setDept(dept);
			Job job = jobDao.get_Info(data.getJob_id());
			data.setJob(job);
			list2.add(i,data);
		}
		return list2;
	}
	@Override
	public Employee get_EmployeeInfo(Integer id) {
		// TODO Auto-generated method stub
		Employee data = employeedao.get_Info(id);
		Dept dept = deptDao.get_Info(data.getDept_id());
		data.setDept(dept);
		Job job = jobDao.get_Info(data.getJob_id());
		data.setJob(job);
		return data;
	}
	@Override
	public void update_EmployeeInfo(Employee data) {
		// TODO Auto-generated method stub
		employeedao.update_Info(data);
	}
	@Override
	public void insert_EmployeeInfo(Employee data) {
		// TODO Auto-generated method stub
		/**
		 * 将职位id和部门id提取，或者不管，因为直接存到数据库了，不管
		 */
		Date date = new Date();    
		String year = String.format("%tY", date);   
		String month = String.format("%tB", date);   
		String day = String.format("%te", date);   
		data.setCreate_date(year+month+day);
		employeedao.insert_Info(data);
	}
	@Override
	public void delete_EmployeeInfo(Integer id) {
		// TODO Auto-generated method stub
		employeedao.delete_Info(id);
	}
	/**
	 * 公告管理
	 */
	@Override
	public List<Notice> get_NoticeList() {
		// TODO Auto-generated method stub
		return noticedao.get_List();
	}
	@Override
	public List<Notice> get_NoticeLikeList(String content) {
		// TODO Auto-generated method stub
		return noticedao.get_LikeList(content);
	}
	@Override
	public Notice get_NoticeInfo(Integer id) {
		// TODO Auto-generated method stub
		return noticedao.get_Info(id);
	}
	@Override
	public void update_NoticeInfo(Notice notice) {
		// TODO Auto-generated method stub
		noticedao.update_Info(notice);
	}
	@Override
	public void insert_NoticeInfo(Notice notice) {
		// TODO Auto-generated method stub
//		Date date = new Date();    
//		String year = String.format("%tY", date);   
//		String month = String.format("%tB", date);   
//		String day = String.format("%te", date);   
//		notice.setCreate_date(year+month+day);
		noticedao.insert_Info(notice);
	}
	@Override
	public void delete_NoticeInfo(Integer id) {
		// TODO Auto-generated method stub
		noticedao.delete_Info(id);
	}
	/**
	 * 文档管理
	 */
	@Override
	public List<Document> get_DocumentList() {
		// TODO Auto-generated method stub
		return documentdao.get_List();
	}
	@Override
	public List<Document> get_DocumentLikeList(String content) {
		// TODO Auto-generated method stub
		return documentdao.get_LikeList(content);
	}
	@Override
	public Document get_DocumentInfo(Integer id) {
		// TODO Auto-generated method stub
		return documentdao.get_Info(id);
	}
	@Override
	public void update_DocumentInfo(Document notice) {
		// TODO Auto-generated method stub
		documentdao.update_Info(notice);
	}
	@Override
	public void insert_DocumentInfo(Document notice) {
		// TODO Auto-generated method stub
//		Date date = new Date();    
//		String year = String.format("%tY", date);   
//		String month = String.format("%tB", date);   
//		String day = String.format("%te", date);   
//		notice.setCreate_date(year+month+day);
		documentdao.insert_Info(notice);
	}
	@Override
	public void delete_DocumentInfo(Integer id) {
		// TODO Auto-generated method stub
		documentdao.delete_Info(id);
	}
	@Override
	public User login(String loginname, String password) {
		// TODO Auto-generated method stub
		User user = userdao.get_login(loginname,password);
		return user;
	}
	@Override
	public List<User> get_UserList() {
		// TODO Auto-generated method stub
		return userdao.get_List();
	}
	@Override
	public List<User> get_UserLikeList(String content) {
		// TODO Auto-generated method stub
		return userdao.get_LikeList(content);
	}
	@Override
	public User get_UserInfo(Integer id) {
		// TODO Auto-generated method stub
		return userdao.get_Info(id);
	}
	@Override
	public void update_UserInfo(User notice) {
		// TODO Auto-generated method stub
		userdao.update_Info(notice);
	}
	@Override
	public void insert_UserInfo(User notice) {
		// TODO Auto-generated method stub
//		Date date = new Date();    
//		String year = String.format("%tY", date);   
//		String month = String.format("%tB", date);   
//		String day = String.format("%te", date);   
//		notice.setCreate_date(year+month+day);
		userdao.insert_Info(notice);
	}
	@Override
	public void delete_UserInfo(Integer id) {
		// TODO Auto-generated method stub
		userdao.delete_Info(id);
	}
}
