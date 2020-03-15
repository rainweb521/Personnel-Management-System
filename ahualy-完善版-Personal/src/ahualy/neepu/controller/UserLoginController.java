package ahualy.neepu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ahualy.neepu.pojo.User;
import ahualy.neepu.pojo.UserVisit;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.common.Constants;
import ahualy.neepu.util.common.IPAddress;
import ahualy.neepu.util.common.RandomCode;
import ahualy.neepu.util.common.SessionSave;
import ahualy.neepu.util.common.ShiroMD5Privacy;
import ahualy.neepu.util.common.UserVisitSingleton;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;

@Controller
public class UserLoginController {
	@Autowired
	@Qualifier("AhualyService")
	
	private AhualyService ahualyservice;//接口实现类对象
	private User user =null;
	
	
	@RequestMapping(value="/login")
	@ResponseBody
	 public ModelAndView login(@RequestParam("loginname") String loginname,
			 @RequestParam("password") String password,
			 @RequestParam("user_input_verifyCode") String user_input_verifyCode,
			 HttpSession session,
			 ModelAndView mv,HttpServletRequest request) throws Exception{ 
		
		/******用户提交登录信息 开始一下步骤******/
	      String changedPswd = ShiroMD5Privacy.privacy(loginname, password);
		  user = ahualyservice.login(loginname, changedPswd);
		
		
		
		if(user != null){
			String ipAddress = null;
	         if (request.getHeader("x-forwarded-for") == null) {  
	             ipAddress = request.getRemoteAddr();
	         }else{
	            if(request.getHeader("x-forwarded-for").length()  > 15){
	                String [] aStr = request.getHeader("x-forwarded-for").split(",");
	                ipAddress = aStr[0];
	            } else{
	                ipAddress = request.getHeader("x-forwarded-for");
	            }
	         }  
	         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	         String terminal = request.getHeader("User-Agent");
	        
	         
	         
	         UserAgent userAgent = UserAgent.parseUserAgentString(terminal);
	         Browser browser = userAgent.getBrowser();
	         OperatingSystem os = userAgent.getOperatingSystem();
	         userAgent.getBrowserVersion();
	         Version version = browser.getVersion(terminal);
	         String iphone = "";
	        if(terminal.contains("Windows NT")){
	        	/*System.out.println("User-Agent:"+terminal);*/
		         //手机型号获取方法实现
		         String pc_regex = " \\((.*); ";
		         Pattern pattern = Pattern.compile(pc_regex);  
		         Matcher matcher = pattern.matcher(terminal); 
		         while (matcher.find()) {  
		        	 iphone = matcher.group(1);
		         }  
		         /*System.out.println("PC系统："+iphone);*/
	             terminal = "PC端";
	        }else{
	        	System.out.println("User-Agent:"+terminal);
		         //手机型号获取方法实现
		         String iphone_regex = "Build\\/(.*)\\) Apple";
		         Pattern pattern = Pattern.compile(iphone_regex);  
		         Matcher matcher = pattern.matcher(terminal); 
		         
		         while (matcher.find()) {  
		        	 iphone = matcher.group(1);
		         }  
		         /*System.out.println("手机型号："+iphone);*/
	            terminal = "移动端";
	            
	        }
	        
	        String Address = null;
	        UserVisit us = UserVisitSingleton.getInstance();
	        
	         if(request.getHeader("host").contains("222605g71y.iask.in")) {
	        	 String urlInfo = "http://www.ip138.com/ips138.asp?ip="+ipAddress;
		         IPAddress ip = new IPAddress();
		         Address = ip.getURLInfo(urlInfo, "gb2312");
		         us.setUser_address(Address);
	         }
	        
	        //将以上各访问信息存入数据库做记录
	        /*************************************/
	       
	        us.setLogin_time(df.format(new Date()).toString());
	        us.setVisit_ip(ipAddress);
	        if(terminal!=null) {
	        	us.setUser_from(terminal);
	        }
	        us.setBrowser(browser.getName());
	        us.setSystem(os.getName());
	        if(version.getVersion()!=null) {
	        	us.setVersion(version.getVersion());
	        }
	        us.setLoginname(loginname);
	        us.setIphone(iphone);
			if(!user_input_verifyCode.toLowerCase().equals(session.getAttribute("code"))){
				mv.addObject("message", "验证码错误!请重新输入！！！");
				// 服务器内部跳转到登录页面
				mv.setViewName("forward:/loginForm");
			}else {
		     //程序运行到这儿，说明，此时用户名和密码都已经正确，验证码也正确输入		
			 //这里再次判断登录访问权限，如果用户注册完之后，没有通过审核，将不给予登录放行
		        if(user.getStatus().getId()==1) {
			        
					session.setAttribute(Constants.USER_SESSION, user);
					session.setAttribute("token", RandomCode.getRandomCode());
					session.setAttribute("lang", "zh_CN");
					
					mv.addObject("token", RandomCode.getRandomCode());
					mv.addObject("lang", "zh_CN");
					// 客户端跳转到main页面
					mv.setViewName("redirect:/index");
					System.out.println("登录用户："+loginname);
			        System.out.println("登录时间："+df.format(new Date()));
			        
			      
			        
		        }else {
		        	mv.addObject("message", "此账号尚未通过审核!");
					// 服务器内部跳转到登录页面
					mv.setViewName("forward:/loginForm");
		        }
			
			}
			
			//ahualyservice.insert_UserVisitInfo(us);  登录的时候先做记录，等到退出系统的时候再执行插入数据库操作
	        //插入执行在ExitController类中实现
			// 这里进行获取用户登录时候的sessionid,即保存当前用户登录的sessionid
			String sessionID = request.getRequestedSessionId();
			String loginname1 = user.getLoginname();
			
			if (!SessionSave.getSessionIdSave().containsKey(loginname1)) {
				//第一次登录，因为没有session，故将sessionid存入map
				SessionSave.getSessionIdSave().put(loginname1, sessionID);
				Constants.FIRST = 1;
			}else if(SessionSave.getSessionIdSave().containsKey(loginname1)&&!sessionID.equals(SessionSave.getSessionIdSave().get(loginname1))){
				SessionSave.getSessionIdSave().remove(loginname1);
				SessionSave.getSessionIdSave().put(loginname1, sessionID);
				Constants.FIRST = 2;
			}
			
		}else{
			// 设置登录失败提示信息
			
			mv.addObject("message", "密码错误!请重新输入！！！");
			// 服务器内部跳转到登录页面
			mv.setViewName("forward:/loginForm");
		}
		
		return mv;
	}

}
