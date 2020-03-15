package ahualy.neepu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ahualy.neepu.pojo.Dept;
import ahualy.neepu.pojo.Employee;
import ahualy.neepu.pojo.Job;
import ahualy.neepu.pojo.Leave;
import ahualy.neepu.pojo.LeaveStatus;
import ahualy.neepu.pojo.LeaveType;
import ahualy.neepu.pojo.User;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.common.SendMail;
import ahualy.neepu.util.page.PageModel;


@Controller
public class LeaveController {
	
	
	@Autowired
	@Qualifier("AhualyService")
    private AhualyService ahualyService;
	
	@Autowired
	private JavaMailSender javaMailSender;  //在spring中配置的发送邮件的bean
	
	@Autowired
	@Qualifier("sendEmail")
	private SendMail sendMail;
	
	@RequestMapping(value="/checkwork/leaveadd",method = RequestMethod.GET)
	public String toleaveadd(Model model) {
		
		
		//查询出请假类型
		List<LeaveType>  leavetype_list = ahualyService.findLeaveType();
		
		model.addAttribute("leavetype_list", leavetype_list);
		
		return "checkwork/leaveadd";
	}
	
	
	@RequestMapping(value="/checkwork/leaveadd",method = RequestMethod.POST)
	public void leaveadd(ModelAndView mv,@RequestParam("leavedays")Integer leavedays,
			@RequestParam("leavetype")Integer leavetype_id,@RequestParam("content")String content,
			@RequestParam("startdata")String startdata,@RequestParam("enddata")String enddata,
			Integer id) {
		
		
		//1.通过传入的当前用户id，查出员工姓名。
		int emp_id = ahualyService.getEmpIdByUserId(id);
		
		Employee employee = ahualyService.get_EmployeeInfo(emp_id);
		
		//employee中包装了Job和Dept
		Integer job_id =employee.getJob().getId();
	    Integer dept_id = employee.getDept().getId();
		
		//2.将员工姓名，岗位，部门 以及请假的天数包装到Leave对象中
		Leave leave = new Leave();
		
		leave.setEmployee(employee);
		leave.setStartdata(startdata);
		leave.setEnddata(enddata);
		leave.setLeavedays(leavedays);
		leave.setContent(content);
		//设置关联方法，进行包装类的关联设置
		this.genericAssociation(job_id,dept_id,leavetype_id,leave);
		
	    //3.执行添加操作
		ahualyService.insert_LeaveInfo(leave);
		
		//4.添加成功后重定向到我的请假页面
		mv.setViewName("redirect:checkwork/leavelist");
		
		
	}
	
	
	
	@RequestMapping("/checkwork/leavelist")
	public String leavelist(Integer id,Model model) {
		
		//用户点击我的请假，查看自己的请假情况，由于所有用户的请假信息存入到了一张表里，所以点击我的请假的时候，需要传入id
		//通过用户id查询自己的请假列表信息。
		
		//1.通过传入的当前用户id，查出员工姓名。
		int emp_id = ahualyService.getEmpIdByUserId(id); //唯一的员工id
		
		List<Leave> leave_list = ahualyService.findLeaveListById(emp_id);
		
		/*if (content!=null&&!content.equals("")){
			leave_list = ahualyService.findLeaveListByIdAndCon(content,id);
		}*/
		
		Integer count = ahualyService.countLeave(emp_id);
		
		model.addAttribute("count", count);
		model.addAttribute("leave_list", leave_list);
		
		return "checkwork/leavelist";
	}
	
	
	
	@RequestMapping("/checkwork/leavedelete")
	public String leavedelete(Integer id) {
		
		ahualyService.delete_LeaveInfo(id);
		
		return "checkwork/leavelist";
	}
	
	
		//跳转请假编辑页面
	    @RequestMapping("/checkwork/toleaveedit")
		public String toleaveedit(Integer id,Model model) {
		
	    //通过id查询出该员工所对应的请假信息
	    
	    Leave leave = ahualyService.findLeaveById(id);
	    
	    //查询出请假类型
	    List<LeaveType>  leavetype_list = ahualyService.findLeaveType();
	  		
	  	model.addAttribute("leavetype_list", leavetype_list);
	    
		model.addAttribute("leave", leave);
		
		return "checkwork/leaveedit";
	}
	
	    //请假更新提交
	    @RequestMapping(value="/checkwork/leaveedit",method = RequestMethod.POST)
		public ModelAndView leaveedit(ModelAndView mv,@RequestParam("leavedays")Integer leavedays,
				@RequestParam("leavetype")Integer leavetype_id,@RequestParam("content")String content,
				@RequestParam("startdata")String startdata,@RequestParam("enddata")String enddata,
				@RequestParam("leave_id")Integer leave_id,Integer id) {
	    	
			//1.通过传入的当前用户id，查出员工姓名。
			int emp_id = ahualyService.getEmpIdByUserId(id);
			Employee employee = ahualyService.get_EmployeeInfo(emp_id);
			
			//employee中包装了Job和Dept
			Integer job_id =employee.getJob().getId();
		    Integer dept_id = employee.getDept().getId();
			
			//2.将员工姓名，岗位，部门 以及请假的天数包装到Leave对象中
			Leave leave = new Leave();
			leave.setId(leave_id);
			leave.setEmployee(employee);
			leave.setStartdata(startdata);
			leave.setEnddata(enddata);
			leave.setLeavedays(leavedays);
			leave.setContent(content);
			//设置关联方法，进行包装类的关联设置
			this.genericAssociation(job_id,dept_id,leavetype_id,leave);
		    //3.执行更新操作
			ahualyService.update_LeaveInfo(leave);
			//4.添加成功后重定向到我的请假页面
			mv.setViewName("/checkwork/leavelist");
			
			return mv;
			
		}
	    
	    
	    //管理员查询所有员工请假信息
	    @RequestMapping(value = "/checkwork/adminleavelist", method = RequestMethod.GET)
	    public String adminleavelist(Integer pageIndex,Model model,String content,Leave leave) {
	    	//创建分页对象
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			List<Leave> job_list = ahualyService.get_LeaveList(leave,pageModel);
			
			//按照关键字content查询
			Integer count = 0;
			if (content!=null&&!content.equals("")){
				count = ahualyService.countAllLeave(content);
				job_list = ahualyService.get_LeaveLikeList(content);
				
			}
			model.addAttribute("count", count);
			model.addAttribute("list",job_list);
			model.addAttribute("pageModel", pageModel);
		
	    	return "checkwork/adminleavelist";
	    }
	    
	    
	  //管理员跳转请假编辑页面
	    @RequestMapping("/checkwork/toadminleaveedit")
		public String toadminleaveedit(Integer id,Model model) {
		
	    //通过id查询出该员工所对应的请假信息
	    
	    Leave leave = ahualyService.findLeaveById(id);
	    
	    //查询出请假类型
	    List<LeaveType>  leavetype_list = ahualyService.findLeaveType();
	  	List<LeaveStatus> leavestatus_list = ahualyService.findLeaveStatus();
	    
	  	model.addAttribute("leavetype_list", leavetype_list);
	  	model.addAttribute("leavestatus_list", leavestatus_list);
	    
		model.addAttribute("leave", leave);
		
		return "checkwork/adminleaveedit";
	}
	    
	    //管理员提交审批结果
	    @RequestMapping(value="/checkwork/adminleaveedit",method = RequestMethod.POST)
		public ModelAndView adminleaveedit(ModelAndView mv,@RequestParam("leavedays")Integer leavedays,
				@RequestParam("leavetype")Integer leavetype_id,@RequestParam("leavestatus")Integer leavestatus_id,
				@RequestParam("leave_id")Integer leave_id) {

			//1.将员工姓名，岗位，部门 以及请假的天数包装到Leave对象中
			Leave leave = new Leave();
			leave.setId(leave_id);
			leave.setLeavedays(leavedays);
			//设置关联方法，进行包装类的关联设置
			this.genericAssociation1(leavetype_id,leavestatus_id,leave);
		    //3.执行更新操作
			ahualyService.update_LeaveInfo(leave);
			
			//4.通过请假id查找到emp表，再通过emp表查找user表，因为user里面有用户的邮箱信息
			Integer emp_id = ahualyService.getEmpIdById(leave_id);
			User user = ahualyService.getStaticId(emp_id);
			
			
			//5.判断请假状态，如果通过审核，发送邮件给普通用户，及时告知
			if(leave!=null&&leave.getLeavestatus().getId()==1) {
				sendMail.sendEmail5(javaMailSender, user);
			}
			
			
			//6.更新成功后重定向到我的请假页面
			mv.setViewName("/checkwork/adminleavelist");
			
			return mv;
			
		}
	    
	//此方法用户关联操作
			private void genericAssociation(Integer job_id,
					Integer dept_id,Integer leavetype_id,Leave leave){
				if(job_id != null){
					Job job = new Job();
					job.setId(job_id);
					leave.setJob(job);
				}
				if(dept_id != null){
					Dept dept = new Dept();
					dept.setId(dept_id);
					leave.setDept(dept);
				}
				if(leavetype_id != null){
					LeaveType leavetype = new LeaveType();
					leavetype.setId(leavetype_id);
					leave.setLeavetype(leavetype);
				}
				
			}
			
			
			//此方法用户关联操作
			private void genericAssociation1(Integer leavetype_id,Integer leavestatus_id,Leave leave){
			
				if(leavetype_id != null){
					LeaveType leavetype = new LeaveType();
					leavetype.setId(leavetype_id);
					leave.setLeavetype(leavetype);
				}
				
				if(leavestatus_id != null){
					LeaveStatus leavestatus =new LeaveStatus();
					leavestatus.setId(leavestatus_id);
					leave.setLeavestatus(leavestatus);
				}
				
				
			}

}
