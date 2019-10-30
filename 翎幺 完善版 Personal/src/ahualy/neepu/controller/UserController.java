package ahualy.neepu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ahualy.neepu.pojo.CreateStaticId;
import ahualy.neepu.pojo.Status;
import ahualy.neepu.pojo.User;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.common.SendMail;
import ahualy.neepu.util.common.ShiroMD5Privacy;
import ahualy.neepu.util.page.PageModel;

@Controller
public class UserController {
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;//接口实现类对象
	
	@Autowired
	private JavaMailSender javaMailSender;  //在spring中配置的发送邮件的bean
	
	@Autowired
	@Qualifier("sendEmail")
	private SendMail sendMail;
	
		@RequestMapping(value="/user/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("/user/list");
			return mv;
		}
		
		
		
		
		// 如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/user/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/user/list";
			return blank;
		}
		
		
		
		//查询所有用户显示用户列表
		@RequestMapping(value="/user/list",method=RequestMethod.GET)
		 public String index(Integer pageIndex,Model model,String content,User user){
			// 创建分页对象
		    PageModel pageModel = new PageModel();
		    if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
			}
			List<User> user_list = ahualyservice.get_UserList(user,pageModel);
			Integer count = 0;
			if (content!=null&&!content.equals("")){
				count = ahualyservice.countUser(content);
				user_list = ahualyservice.get_UserLikeList(content);
			}
			model.addAttribute("count",count);
			model.addAttribute("list",user_list);
			model.addAttribute("pageModel", pageModel);
			return "user/list";
		}
		
		
		@RequestMapping(value="/user/toadd",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				User user = ahualyservice.get_UserInfo(id);
				model.addAttribute("job",user);
			}
			return "/user/add";
		}
		
		//用户更新页面跳转
		@RequestMapping(value="/user/edit",method=RequestMethod.GET)
		 public String edit(Model model,Integer id){
			if(id!=null){
				User user = ahualyservice.get_UserInfo(id);
				user.setPassword(user.getPassword().substring(0, 6));
				model.addAttribute("user",user);
			}
			
			List<Status>  status_list = ahualyservice.findAllStatus();
			model.addAttribute("status_list", status_list);
			return "/user/edit";
		}
		
		
		//用户更新操作
		@RequestMapping(value="/user/edit",method=RequestMethod.POST)
		 public String toedit(Integer status_id,Integer pageIndex,Model model,Integer id,@ModelAttribute User user){
			    
				PageModel pageModel = new PageModel();
				if(pageIndex != null){
						pageModel.setPageIndex(pageIndex);
				}
		    	this.genericAssociation(status_id,user);
				ahualyservice.update_UserInfo(user);
				//提交修改之前，判断用户的satus状态,如果状态审核为1，就发送邮件给用户，提醒可以登录
				if(user!=null&&user.getStatus().getId()==1) {
					sendMail.sendEmail1(javaMailSender, user);
				}
				if(user!=null&&user.getStatus().getId()==2) {
					sendMail.sendEmail2(javaMailSender, user);
				}
				
				List<User> user_list = ahualyservice.get_UserList(user,pageModel);
				model.addAttribute("list",user_list);
				model.addAttribute("pageModel", pageModel);
				return "redirect:/user/list";
		}
		
		
		
		//用户注册
		@RequestMapping(value="/user/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,@ModelAttribute User user ,Integer id){
			    //1.首先登录名进行判断，如果登录名已经存在，将不能进行插入
			    User user1 = ahualyservice.findUserByLoginAndName(user.getLoginname(), user.getUsername());
			    User user2 = ahualyservice.findUserByLogin(user.getLoginname());
			    User user3 = ahualyservice.findUserByName(user.getUsername());
			    if(user1==null&&user2==null&&user3==null) {
			    	//检查邮箱是否已经被注册
			    	//通过用户信息提交的邮箱号去user_inf表中去查询是否有邮箱所对应的用户
			    	User user4 = ahualyservice.findUserByEmail(user.getEmail());
			    	if(user4==null) {
			    	//对明文密码进行加密
				    user.setPassword(ShiroMD5Privacy.privacy(user.getLoginname(), user.getPassword()));
				  //这个地方需要生成一个id，这个id是为了给che_id,sal_id,con_id 提供的一个值
				    //那这个id该如何去生成，怎么就能唯一呢
				    //可以去随机生成一4位数的数字，生成之后，这个数字必须保证唯一，所以，先生成数字，再去user_inf表中去查找看存不存在
				    //如果不存在，就可以将这个数字设置为id，否则，继续去生成，完成之后，就可以进行插入操作了
				       //这里随机生成的数字不能确保唯一性，所以，定义一个全局的id，这个id初始化的值每次需要从数据库获取到，确保唯一
				      CreateStaticId createStaticId = new CreateStaticId(ahualyservice.getStaticId().getStaticId());
				      Integer staticId=createStaticId.getStaticId();
				      System.out.println("插入之前staticId==="+staticId);
					    	user.setEmp_id(staticId);
					    	user.setChe_id(staticId);
					    	user.setCon_id(staticId);
					    	user.setSal_id(staticId);
					    	ahualyservice.insert_UserInfo(user);
					  //插入成功之后，这里执行更新工作，就是将这个值更新掉
					   ahualyservice.updata_staticId(staticId);	
					  System.out.println("插入之后staticId==="+staticId);
					//在注册成功之后，我需要通过获取邮箱信息给用户发送邮件，告诉用户管理员会在2小时之内进行身份审核
					//审核完之后，会发送邮件给用户，用户就可以登录系统了
					 sendMail.sendEmail(javaMailSender,user);
					mv.setViewName("redirect:/user/list");
				    return mv;
			    	}
			    	else {
			    		mv.addObject("message", "此邮箱已被注册，请更换你的邮箱");
			    		mv.setViewName("redirect:/user/toadd");
						return mv;
			    	}
			    	
			    }else {
			    	mv.addObject("message", "此用户已经被注册，请更换你的用户名和登录名");
			    	mv.setViewName("redirect:/user/toadd");
					return mv;
			    }
			    
		}
		
		
		
		
		
		
		
		@RequestMapping(value="/user/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			System.out.println(id);
			if(id!=null){
				ahualyservice.delete_UserInfo(id);
			}
		}
		
		
		private void genericAssociation(Integer status_id,User user){
			
			if(status_id != null){
				Status status = new Status();
				status.setId(status_id);
				user.setStatus(status);
			}
			
			
		}
		
}
