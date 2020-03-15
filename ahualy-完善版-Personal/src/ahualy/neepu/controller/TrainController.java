package ahualy.neepu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ahualy.neepu.pojo.Completion;
import ahualy.neepu.pojo.Dept;
import ahualy.neepu.pojo.Employee;
import ahualy.neepu.pojo.Job;
import ahualy.neepu.pojo.Train;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.page.PageModel;
@Controller
public class TrainController {
	
	/**
	 * 主要实现的业务
	 * 1.普通员工只能查询自己的培训信息，通过员工id查询即可
	 * 2.管理员，添加员工培训信息，查询所有员工信息（包括模糊查询），修改员工信息，删除员工信息。
	 */
	
	@Autowired
	@Qualifier("AhualyService")
    private AhualyService ahualyService;
	
	
	@RequestMapping(value="/train")
	public ModelAndView Exit(ModelAndView mv) {
		mv.setViewName("/train/train");
		return mv;
	}
	
	//  普通员工自己查看自己的培训信息列表
	@RequestMapping(value="/train/trainlist")
	public String trainlist(Integer id,Model model) {
		
				//用户点击我的培训，查看自己的培训情况，由于所有用户的培训信息存入到了一张表里，所以点击我的培训的时候，需要传入id
				//通过用户id查询自己的培训列表信息。
				
				//1.通过传入的当前用户id，查出员工姓名。
				int emp_id = ahualyService.getEmpIdByUserId(id); //唯一的员工id
				
				List<Train> train_list = ahualyService.findTrainListById(emp_id);
				
				/*if (content!=null&&!content.equals("")){
					leave_list = ahualyService.findLeaveListByIdAndCon(content,id);
				}*/
				
				Integer count = ahualyService.countTrain(emp_id);
				
				model.addAttribute("count", count);
				model.addAttribute("train_list", train_list);
		
		return "train/trainlist";
	}
	
	
	
	@RequestMapping(value="/train/admintrainlist",method=RequestMethod.GET)
	public String admintrainlist(Integer pageIndex,Model model,String content,Train train) {
		
		// 创建分页对象
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Train> train_list = ahualyService.get_TrainList(train,pageModel);
			Integer count = 0;
			if (content!=null&&!content.equals("")){
				count = ahualyService.countAllTrain(content);
				train_list = ahualyService.get_TrainLikeList(content);
			}
			model.addAttribute("count",count);
			model.addAttribute("train_list",train_list);
			model.addAttribute("pageModel", pageModel);
		
		return "train/admintrainlist";
	}
	
	//管理员培训添加页面跳转  
	@RequestMapping(value="/train/trainadd",method = RequestMethod.GET)
	public String  trainadd(Model model) {
		//需要查询出一些固定数据
		List<Completion> completion_list = ahualyService.findCompletion();
		
		model.addAttribute("completion_list", completion_list);
		
		return "train/add";
	}
	
	   //管理员培训添加提交  
		@RequestMapping(value="/train/add",method = RequestMethod.POST)
		public ModelAndView  totrainadd(ModelAndView mv,@RequestParam("employee_name")String employee_name,@RequestParam("content") String content,
				@RequestParam("startdata") String startdata,@RequestParam("enddata") String enddata,
				@RequestParam("totallength") Integer totallength,@RequestParam("completion") Integer completion,
				@RequestParam("grade") Integer grade,@RequestParam("id") Integer id) {
			
			//1.通过提交的员工姓名去查询员工表，如果员工姓名是错误的（即不在数据库，将不能进行添加员工培训信息）
			//保证有这个员工就ok
			Integer employee_id = ahualyService.get_EmployeeByName(employee_name);

			  if(employee_id!=null){
				  //根据employee_id去查询员工的信息，前端只需要添加培训信息即可。
				    Employee e =  ahualyService.get_EmployeeInfo(employee_id);
				    Integer job_id1 =e.getJob().getId();
				    Integer dept_id1 = e.getDept().getId();
				  
				    Train train = new Train();
				    train.setContent(content);
				    train.setStartdata(startdata);
				    train.setEnddata(enddata);
				    train.setTotallength(totallength);
				    train.setGrade(grade);
				    
				    this.genericAssociation1(job_id1, dept_id1,employee_id,completion,train);
				    ahualyService.insert_TrainInfo(train);
				    mv.setViewName("redirect:/train/admintrainlist");
				    return mv;
			}else{
				mv.addObject("message", "没有查到此员工，请联系管理员先添加此员工!");
				mv.setViewName("redirect:/train/trainadd");
				return mv;
			}
		}
	
	
	   //管理员培训编辑页面跳转  
		@RequestMapping(value="/train/edit",method = RequestMethod.GET)
		public String  trainedit(Integer id,Model model) {
			
			Train train = ahualyService.findTrainById(id);
			
			List<Completion> completion_list = ahualyService.findCompletion();
			
			model.addAttribute("completion_list", completion_list);
			
			model.addAttribute("train", train);
			
			return "train/edit";
		}
		
		
		
		//管理员培训编辑结果提交
		@RequestMapping(value="/train/edit",method = RequestMethod.POST)
		public ModelAndView  totrainedit(ModelAndView mv,@RequestParam("content") String content,
				@RequestParam("startdata") String startdata,@RequestParam("enddata") String enddata,
				@RequestParam("totallength") Integer totallength,@RequestParam("completion") Integer completion,
				@RequestParam("grade") Integer grade,@RequestParam("id") Integer id) {
			
			//更新操作只更新培训列表中自己所以独有内容
			Train train = new Train();
			
			train.setId(id);
			train.setContent(content);
		    train.setStartdata(startdata);
		    train.setEnddata(enddata);
		    train.setTotallength(totallength);
		    train.setGrade(grade);
			
		    Completion completion2 = new Completion();
			completion2.setId(completion);
			train.setCompletion(completion2);
			
			ahualyService.update_TrainInfo(train);
		    mv.setViewName("redirect:/train/admintrainlist");
			
			return mv;
		}
	
		
		
		//管理员删除员工培训信息
		@RequestMapping(value="/train/delete",method = RequestMethod.GET)
		public void delete(Integer id) {
			ahualyService.delete_Train(id);
		}
		
		
		private void genericAssociation1(Integer job_id,
				Integer dept_id,Integer employee_id,Integer completion, Train train){
			if(job_id != null){
				Job job = new Job();
				job.setId(job_id);
				train.setJob(job);
			}
			if(dept_id != null){
				Dept dept = new Dept();
				dept.setId(dept_id);
				train.setDept(dept);
			}
			if (completion !=null) {
				Completion completion2 = new Completion();
				completion2.setId(completion);
				train.setCompletion(completion2);
			}
			
			if(employee_id != null){
				Employee employee= new Employee();
				employee.setId(employee_id);
				train.setEmployee(employee);
			}
			
		}
}
