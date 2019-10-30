package ahualy.neepu.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ahualy.neepu.pojo.Dept;
import ahualy.neepu.pojo.Employee;
import ahualy.neepu.pojo.Job;
import ahualy.neepu.pojo.Salary;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.page.PageModel;

@Controller
public class SalaryController {
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	   //如果在目录下输入为空，则跳转到指定链接
		@RequestMapping(value="/salary/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("salary/list");
			return mv;
		}
		// 如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/salary/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/salary/list";
			return blank;
		}
		
		@RequestMapping(value="/salary/list",method=RequestMethod.GET)
		 public String index(Integer pageIndex,Model model,String content,Salary salary){
			// 创建分页对象
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			List<Salary> job_list = ahualyservice.get_SalaryList(salary,pageModel);
			Integer count = 0;
			if (content!=null&&!content.equals("")){
				count = ahualyservice.countSalary(content);
				job_list = ahualyservice.get_SalaryLikeList(content);
			}
			model.addAttribute("count",count);
			model.addAttribute("list",job_list);
			model.addAttribute("pageModel", pageModel);
			return "salary/list";
		}
		
		@RequestMapping(value="/salary/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				Salary salary = ahualyservice.get_SalaryInfo(id);
				model.addAttribute("job",salary);
			}
			List<Dept> dept_list = ahualyservice.findAllDept();
			List<Job> job_list = ahualyservice.findAllJob();
			model.addAttribute("job_list", job_list);
			model.addAttribute("dept_list",dept_list);
			return "/salary/add";
		}
		
		@RequestMapping(value="/salary/edit",method=RequestMethod.GET)
		 public String edit(Model model,Integer id){
			if(id!=null){
				Salary salary = ahualyservice.get_SalaryInfo(id);
				model.addAttribute("salary",salary);
			}
			List<Dept> dept_list = ahualyservice.findAllDept();
			List<Job> job_list = ahualyservice.findAllJob();
			model.addAttribute("job_list", job_list);
			model.addAttribute("dept_list",dept_list);
			return "/salary/edit";
		}
		
		
		@RequestMapping(value="/salary/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,Integer job_id,Integer dept_id,String employee_name,@ModelAttribute Salary salary ,Integer id
				 ){
			Integer employee_id =null;
			if(id!=null){
				this.genericAssociation(job_id, dept_id,employee_name,salary);
				ahualyservice.update_SalaryInfo(salary);
			}else{
				 employee_id = ahualyservice.get_EmployeeByName(employee_name);
				  if(employee_id!=null&&ahualyservice.get_SalaryEmp_id(employee_id)==null){
					  //根据employee_id去查询员工的信息，前端只需要添加薪资即可。
					    Employee e =  ahualyservice.get_EmployeeInfo(employee_id);
					    Integer job_id1 =e.getJob().getId();
					    Integer dept_id1 = e.getDept().getId();
					  //只需要将id设置为员工信息的id（即全局id即可）
					    salary.setId(employee_id);
					    this.genericAssociation1(job_id1, dept_id1,employee_id,salary);
					    ahualyservice.insert_SalaryInfo(salary);
					    mv.setViewName("redirect:/salary/list");
					    return mv;
				}else{
					mv.addObject("message", "没有查到此员工，请联系管理员先添加此员工!");
					mv.setViewName("redirect:/salary/add");
					return mv;
				}
			}
			mv.setViewName("redirect:/salary/list");
			return mv;
		}
		
		@RequestMapping(value="/salary/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			System.out.println(id);
			if(id!=null){
				ahualyservice.delete_Salary(id);
			}
		}
		
		private void genericAssociation1(Integer job_id,
				Integer dept_id,Integer employee_id,Salary salary){
			if(job_id != null){
				Job job = new Job();
				job.setId(job_id);
				salary.setJob(job);
			}
			if(dept_id != null){
				Dept dept = new Dept();
				dept.setId(dept_id);
				salary.setDept(dept);
			}
			if(employee_id != null){
				Employee employee= new Employee();
				employee.setId(employee_id);
				salary.setEmployee(employee);
			}
			
		}
		
		private void genericAssociation(Integer job_id,
				Integer dept_id,String employee_name,Salary salary){
			if(job_id != null){
				Job job = new Job();
				job.setId(job_id);
				salary.setJob(job);
			}
			if(dept_id != null){
				Dept dept = new Dept();
				dept.setId(dept_id);
				salary.setDept(dept);
			}
			if(employee_name != null){
				Employee employee= new Employee();
				employee.setName(employee_name);
				salary.setEmployee(employee);
			}
			
		}
}
