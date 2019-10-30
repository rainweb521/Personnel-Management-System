package ahualy.neepu.controller;

import java.util.List;
//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ahualy.neepu.pojo.Notice;
import ahualy.neepu.pojo.User;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.common.SendMail;
import ahualy.neepu.util.page.PageModel;
import io.goeasy.GoEasy;

@Controller
public class NoticeController {
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	
	@Autowired
	private JavaMailSender javaMailSender;  //在spring中配置的发送邮件的bean
	
	@Autowired
	@Qualifier("sendEmail")
	private SendMail sendMail;
	
	//private Map<String, Integer> map = Constants.NOTENUMBER;
	//private boolean flag;
	   //如果在目录下输入为空，则跳转到指定链接
		@RequestMapping(value="/notice/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("notice/list");
			return mv;
		}
		//如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/notice/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/notice/list";
			return blank;
		}
		@RequestMapping(value="/notice/list",method=RequestMethod.GET)
		 public String index(Integer pageIndex,Model model,String content,Notice notice){
			// 创建分页对象
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
					pageModel.setPageIndex(pageIndex);
			  }
			List<Notice> job_list = ahualyservice.get_NoticeList(notice,pageModel);
			Integer count = 0;
			if (content!=null&&!content.equals("")){
				count = ahualyservice.countNotice(content);
				job_list = ahualyservice.get_NoticeLikeList(content);
				
			}
			model.addAttribute("count",count);
			model.addAttribute("list",job_list);
			model.addAttribute("pageModel", pageModel);
			return "notice/list";
		}
		
		
		@RequestMapping(value="/notice/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				Notice job = ahualyservice.get_NoticeInfo(id);
				model.addAttribute("job",job);
			}
			return "/notice/add";
		}
		@RequestMapping(value="/notice/edit",method=RequestMethod.GET)
		 public String edit(Model model,Integer id){
			if(id!=null){
				Notice job = ahualyservice.get_NoticeInfo(id);
				model.addAttribute("job",job);
			}
			return "/notice/edit";
		}
		@RequestMapping(value="/notice/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,@ModelAttribute Notice notice ,Integer id,Integer user_id){
			System.out.println(id);
			if(id!=null){
				ahualyservice.update_NoticeInfo(notice);
				//更新公告的同时，发送邮件给员工，及时告知
				List<User> sqluser = ahualyservice.get_UserEmail();
				String message = notice.getContent();
				sendMail.sendEmail4(javaMailSender, sqluser, message);
			}else{
				this.genericAssociation(user_id,notice);
				ahualyservice.insert_NoticeInfo(notice);
				//flag =true;
				//插入之后直接出发GoEasy提供消息发送
				GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-0706aa93d6614a2db50b660458b42ff5");
				goEasy.publish("myChannel","有新的公告发布，请转到公告页查看！！！");
				//添加公告的同时，发送邮件给员工，及时告知
				List<User> sqluser = ahualyservice.get_UserEmail();
				String message = notice.getContent();
				sendMail.sendEmail3(javaMailSender, sqluser, message);
			}
			mv.setViewName("redirect:/notice/list");
			return mv;
		}
		
		@RequestMapping(value="/notice/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			if(id!=null){
				ahualyservice.delete_NoticeInfo(id);
			}
		}
		
		/**
		 * ajax轮询方式实现
		 * @param user_id
		 * @param notice
		 */
		/*@RequestMapping(value="/notice/newMessage")
		@ResponseBody
		 public Map<String, Integer> newMessage(){
			if(flag==true) {
				     flag=false;
					 return map;
				}else {
				    return null;
			   }
			
		}
		*/
		
		
		
		
		
		
		private void genericAssociation(Integer user_id,Notice notice){
			if(user_id != null){
				User user = new User();
				user.setId(user_id);
				notice.setUser(user);
			}
		}
}
