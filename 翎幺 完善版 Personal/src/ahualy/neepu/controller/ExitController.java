package ahualy.neepu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ahualy.neepu.pojo.User;
import ahualy.neepu.pojo.UserVisit;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.common.Constants;
import ahualy.neepu.util.common.SessionSave;
import ahualy.neepu.util.common.UserVisitSingleton;

@Controller
public class ExitController {
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	
	@RequestMapping(value="/exit")
	public ModelAndView Exit(ModelAndView mv,HttpSession session) {
		mv.setViewName("forward:/loginForm");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println("退出系统时间："+df.format(new Date()));
		UserVisit us = UserVisitSingleton.getInstance();
		us.setExit_time(df.format(new Date()).toString());
		ahualyservice.insert_UserVisitInfo(us);
		User user = (User) session.getAttribute(Constants.USER_SESSION);
		SessionSave.getSessionIdSave().remove(user.getLoginname());
		//session.invalidate();
		return mv;
	}
}
