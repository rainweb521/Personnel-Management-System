package ahualy.neepu.service;

import java.util.List;
import java.util.Map;

import ahualy.neepu.pojo.Checkwork;
import ahualy.neepu.pojo.Completion;
import ahualy.neepu.pojo.Confidentialitycontract;
import ahualy.neepu.pojo.Contract;
import ahualy.neepu.pojo.CreateStaticId;
import ahualy.neepu.pojo.Dept;
import ahualy.neepu.pojo.Document;
import ahualy.neepu.pojo.Education;
import ahualy.neepu.pojo.Employee;
import ahualy.neepu.pojo.Job;
import ahualy.neepu.pojo.Laborcontract;
import ahualy.neepu.pojo.Leave;
import ahualy.neepu.pojo.LeaveStatus;
import ahualy.neepu.pojo.LeaveType;
import ahualy.neepu.pojo.Notice;
import ahualy.neepu.pojo.RegistCode;
import ahualy.neepu.pojo.Salary;
import ahualy.neepu.pojo.Sex;
import ahualy.neepu.pojo.Status;
import ahualy.neepu.pojo.Train;
import ahualy.neepu.pojo.TrainData;
import ahualy.neepu.pojo.Traincontract;
import ahualy.neepu.pojo.User;
import ahualy.neepu.pojo.UserVisit;
import ahualy.neepu.util.page.PageModel;


public interface AhualyService {
	/**
	 * 部门信息的service
	 * @param content
	 * @return
	 */
	List<Dept> findAllDept(String content);
	void addDept(Dept dept);
	Dept get_deptByname(String name);
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
	Job get_jobByname(String name);
	Job get_JobInfo(Integer id);
	void update_JobInfo(Job job);
	void insert_JobInfo(Job job);
	void delete_JobInfo(Integer id);
	/**
	 * 员工信息的service
	 */
	List<Employee> get_EmployeeList(Employee employee,PageModel pageModel);
	List<Employee> get_EmployeeLikeList(String content);
	Integer countEmployee(String content);
	Integer get_EmployeeByName(String employee_name);
	Employee get_EmployeeInfo(Integer id);
	void update_EmployeeInfo(Employee employee);
	void insert_EmployeeInfo(Employee employee);
	void delete_EmployeeInfo(Integer id);
	
	/**
	 * 员工考勤信息
	 * @return
	 */
	List<Checkwork> get_CheckworkList(Checkwork checkwork,PageModel pageModel);
	List<Checkwork> get_CheckworkLikeList(String content);
	Integer countCheckwork(String content);
	Checkwork get_CheckworkInfo(Integer id);
	Checkwork get_CheckworkEmp_id(Integer id);
	void update_CheckworkInfo(Checkwork checkwork);
	void insert_CheckworkInfo(Checkwork checkwork);
	void delete_Checkwork(Integer id);
	
	/**
	 * 员工合同信息
	 * @return
	 */
	List<Contract> get_ContractList(Contract contract,PageModel pageModel);
	List<Contract> get_ContractLikeList(String content);
	Integer countContract(String content);
	Contract get_ContractInfo(Integer id);
	Contract get_ContractEmp_id(Integer id);
	void update_ContractInfo(Contract contract);
	void insert_ContractInfo(Contract contract);
	void delete_Contract(Integer id);
	
	
	
	
	/**
	 * 员工薪酬信息
	 * @return
	 */
	List<Salary> get_SalaryList(Salary salary,PageModel pageModel);
	List<Salary> get_SalaryLikeList(String content);
	Integer countSalary(String content);
	Salary get_SalaryEmp_id(Integer id);
	Salary get_SalaryInfo(Integer id);
	void update_SalaryInfo(Salary salary);
	void insert_SalaryInfo(Salary salary);
	void delete_Salary(Integer id);
	
	
	List<Notice> get_NoticeList(Notice notice,PageModel pageModel);
	List<Notice> get_NoticeLikeList(String content);
	Integer countNotice(String content);
	Notice get_NoticeInfo(Integer id);
	void update_NoticeInfo(Notice notice);
	void insert_NoticeInfo(Notice notice);
	void delete_NoticeInfo(Integer id);
	

	List<Document> get_DocumentList(Document document,PageModel pageModel);
	List<Document> get_DocumentLikeList(String content);
	Integer countDocument(String content);
	Document get_DocumentInfo(Integer id);
	void update_DocumentInfo(Document notice);
	void insert_DocumentInfo(Document notice);
	void delete_DocumentInfo(Integer id);
	

	User login(String loginname, String password);
	User findUserByLoginAndName(String loginname,String username);
	User findUserByLogin(String loginname);
	
	User findUserByEmail(String email);
	
	User findUserByName(String username);
	
	void toUpdatePassword(String loginname,String password);
	
	List<User> get_UserList(User user,PageModel pageModel);
	List<User> get_UserLikeList(String content);
	List<User> get_UserEmail();
	Integer countUser(String content);
	Integer getEmpIdByUserId(Integer id);
	User get_UserInfo(Integer id);
	void update_UserInfo(User user);
	void insert_UserInfo(User user);
	void delete_UserInfo(Integer id);
	User getStaticId(Integer staticid);
	CreateStaticId getStaticId();
	void updata_staticId(Integer staticId);
	
	
	/******************************/
	
	void insert_UserVisitInfo(UserVisit userVisit);
	List<UserVisit> get_UserVisitList(UserVisit userVisit,PageModel pageModel);
	List<UserVisit> get_UserVisitLinkList(String content);
	Integer countUserVisit(String content);
	void delete_UserVisitInfo(Integer id);
	
	
	
	/**
     * 饼状图数据
     * @return
     */
    public List<Map<String,Object>> pieData();
    /**
     * 柱状图
     * @return
     */
    public List<Map<String,Object>> pieData1();
    
    /**
     * 折线图
     * @return
     */
    public List<Map<String,Object>> pieData2();
    
    /**
     * 地图
     * @return
     */
    public Map<String,Integer> pieData3();
    
    
    
    
    
    List<Confidentialitycontract> findAllConfidentialitycontract(String content);
	List<Confidentialitycontract> findAllConfidentialitycontract();
	
	
	
	List<Laborcontract> findAllLaborcontract(String content);
	List<Laborcontract> findAllLaborcontract();
	
	
	List<Traincontract> findAllTraincontract(String content);
	List<Traincontract> findAllTraincontract();
	
	List<Sex> findAllSex(String content);
	List<Sex> findAllSex();
	
	List<Education> findAllEducation(String content);
	List<Education> findAllEducation();
	
	
	
	
	List<Status> findAllStatus(String content);
	List<Status> findAllStatus();
	
	Integer findCon_id();
	Integer findSal_id();
	Integer findChe_id();
	
	
	/**
	 * 用户注册码
	 */
	
	String findCode(String code);
	void addCode(RegistCode registCode);
	
	
	//为shiro些一个根据登录名查用户信息，即登录名和密码的方法
	User findUserMessageByLoginname(String loginname);
	
	
	//请假方法
	
	List<LeaveType> findLeaveType();
	List<LeaveStatus> findLeaveStatus();
	void insert_LeaveInfo(Leave leave);
	List<Leave> findLeaveListById(Integer id);
	void delete_LeaveInfo(Integer id);
	Leave findLeaveById(Integer id);
	void update_LeaveInfo(Leave leave);
	Integer countLeave(Integer id);
	Integer getEmpIdById(Integer id);
	
	List<Leave> get_LeaveList(Leave leave,PageModel pageModel);
	Integer countAllLeave(String content);
	List<Leave> get_LeaveLikeList(String content);
	
	//培训方法
	
	List<Completion> findCompletion();
	//普通用户根据用户id查询自己的培训信息列表
	List<Train> findTrainListById(Integer id);
	//管理员用户查询所有员工培训信息列表(模糊查询)
	Train findTrainById(Integer id);
	List<Train> get_TrainList(Train train,PageModel pageModel);
	Integer countAllTrain(String content);
	Integer countTrain(Integer id);
	List<Train> get_TrainLikeList(String content);
	//管理员更新，添加，删除员工培训信息
	void update_TrainInfo(Train train);
	void insert_TrainInfo(Train train);
	void delete_Train(Integer id);
	
	
	//培训资源
	List<TrainData> get_TrainDataList(TrainData trainData,PageModel pageModel);
	List<TrainData> get_TrainDataLikeList(String content);
	Integer countTrainData(String content);
	TrainData get_TrainDataInfo(Integer id);
	void update_TrainDataInfo(TrainData notice);
	void insert_TrainDataInfo(TrainData notice);
	void delete_TrainDataInfo(Integer id);
}
