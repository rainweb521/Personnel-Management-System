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

import ahualy.neepu.pojo.Confidentialitycontract;
import ahualy.neepu.pojo.Contract;
import ahualy.neepu.pojo.Dept;
import ahualy.neepu.pojo.Employee;
import ahualy.neepu.pojo.Job;
import ahualy.neepu.pojo.Laborcontract;
import ahualy.neepu.pojo.Traincontract;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.page.PageModel;

@Controller
public class ContractController {
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	    //如果在目录下输入为空，则跳转到指定链接
		@RequestMapping(value="/contract/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("contract/list");
			return mv;
		}
		// 如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/contract/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/contract/list";
			return blank;
		}
		
		@RequestMapping(value="/contract/list",method=RequestMethod.GET)
		 public String index(Integer pageIndex,Model model,String content,Contract contract){
			
			// 创建分页对象
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
						
			List<Contract> job_list = ahualyservice.get_ContractList(contract,pageModel);
			Integer count = 0;
		    if(content!=null&&!content.equals("")){
			count = ahualyservice.countContract(content);
			job_list = ahualyservice.get_ContractLikeList(content);
			}
		    model.addAttribute("count",count);
			model.addAttribute("list",job_list);
			model.addAttribute("pageModel", pageModel);
			return "contract/list";
		}
		
		@RequestMapping(value="/contract/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				Contract contract = ahualyservice.get_ContractInfo(id);
				model.addAttribute("job",contract);
			}
			List<Dept> dept_list = ahualyservice.findAllDept();
			List<Job> job_list = ahualyservice.findAllJob();
			model.addAttribute("job_list", job_list);
			model.addAttribute("dept_list",dept_list);
			return "/contract/add";
		}
		
		@RequestMapping(value="/contract/edit",method=RequestMethod.GET)
		 public String edit(Model model,Integer id){
			if(id!=null){
				Contract contract = ahualyservice.get_ContractInfo(id);
				model.addAttribute("contract",contract);
			}
			List<Dept> dept_list = ahualyservice.findAllDept();
			List<Job> job_list = ahualyservice.findAllJob();
			List<Traincontract> traincontract_list = ahualyservice.findAllTraincontract();
			List<Laborcontract> laborcontract_list = ahualyservice.findAllLaborcontract();
			List<Confidentialitycontract> confidentialitycontract_list = ahualyservice.findAllConfidentialitycontract();
			model.addAttribute("job_list", job_list);
			model.addAttribute("dept_list",dept_list);
			model.addAttribute("traincontract_list",traincontract_list);
			model.addAttribute("laborcontract_list",laborcontract_list);
			model.addAttribute("confidentialitycontract_list",confidentialitycontract_list);
			return "/contract/edit";
		}
		
		
		@RequestMapping(value="/contract/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,
				 Integer job_id,Integer dept_id,String employee_name
				 ,Integer trainContract_id,Integer laborContract_id,Integer confidentialityContract_id,
				 @ModelAttribute Contract contract ,Integer id){
			Integer employee_id =null;
			if(id!=null){
				this.genericAssociation(job_id,trainContract_id,laborContract_id, confidentialityContract_id,dept_id,employee_name,contract);
				ahualyservice.update_ContractInfo(contract);
			}else{
				employee_id = ahualyservice.get_EmployeeByName(employee_name);
				if(employee_id!=null&&ahualyservice.get_ContractEmp_id(employee_id)==null) {
					 //根据employee_id去查询员工的信息，前端只需要添加薪资即可。
				    Employee e =  ahualyservice.get_EmployeeInfo(employee_id);
				    Integer job_id1 =e.getJob().getId();
				    Integer dept_id1 = e.getDept().getId();
				  //只需要将id设置为员工信息的id（即全局id即可）
				    contract.setId(employee_id);
					this.genericAssociation1(job_id1,trainContract_id,laborContract_id, confidentialityContract_id,dept_id1,employee_id,contract);
					ahualyservice.insert_ContractInfo(contract);
					mv.setViewName("redirect:/contract/list");
					return mv;
				}else{
					mv.addObject("message", "没有查到此员工，请联系管理员先添加此员工!");
					mv.setViewName("redirect:/contract/add");
					return mv;
				}
				
			}
			mv.setViewName("redirect:/contract/list");
			return mv;
		}
		
		@RequestMapping(value="/contract/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			System.out.println(id);
			if(id!=null){
				ahualyservice.delete_Contract(id);
			}
		}
		
		
		private void genericAssociation(Integer job_id,Integer trainContract_id,Integer laborContract_id,Integer confidentialityContract_id,
				Integer dept_id,String employee_name,Contract contract){
			if(job_id != null){
				Job job = new Job();
				job.setId(job_id);
				contract.setJob(job);
			}
			if(dept_id != null){
				Dept dept = new Dept();
				dept.setId(dept_id);
				contract.setDept(dept);
			}
			
			if(trainContract_id != null){
				Traincontract traincontract = new Traincontract();
				traincontract.setId(trainContract_id);
				contract.setTrainContract(traincontract);
			}
			if(laborContract_id != null){
				Laborcontract laborcontract = new Laborcontract();
				laborcontract.setId(laborContract_id);
				contract.setLaborContract(laborcontract);
			}
			if(confidentialityContract_id != null){
				Confidentialitycontract confidentialitycontract = new Confidentialitycontract();
				confidentialitycontract.setId(confidentialityContract_id);
				contract.setConfidentialityContract(confidentialitycontract);
			}
			if(employee_name != null){
				Employee employee= new Employee();
				employee.setName(employee_name);
				contract.setEmployee(employee);
			}
			
		}
		
		
		private void genericAssociation1(Integer job_id,Integer trainContract_id,Integer laborContract_id,Integer confidentialityContract_id,
				Integer dept_id,Integer employee_id,Contract contract){
			if(job_id != null){
				Job job = new Job();
				job.setId(job_id);
				contract.setJob(job);
			}
			if(dept_id != null){
				Dept dept = new Dept();
				dept.setId(dept_id);
				contract.setDept(dept);
			}
			
			if(trainContract_id != null){
				Traincontract traincontract = new Traincontract();
				traincontract.setId(trainContract_id);
				contract.setTrainContract(traincontract);
			}
			if(laborContract_id != null){
				Laborcontract laborcontract = new Laborcontract();
				laborcontract.setId(laborContract_id);
				contract.setLaborContract(laborcontract);
			}
			if(confidentialityContract_id != null){
				Confidentialitycontract confidentialitycontract = new Confidentialitycontract();
				confidentialitycontract.setId(confidentialityContract_id);
				contract.setConfidentialityContract(confidentialitycontract);
			}
			if(employee_id != null){
				Employee employee= new Employee();
				employee.setId(employee_id);
				contract.setEmployee(employee);
			}
			
		}
			
}
