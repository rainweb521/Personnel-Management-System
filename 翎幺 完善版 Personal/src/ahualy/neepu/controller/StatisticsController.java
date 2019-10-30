package ahualy.neepu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ahualy.neepu.service.AhualyService;

@Controller
public class StatisticsController {
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	@RequestMapping(value="/statistics")
    public String toShows(Model model,HttpSession session) {
		return "/statistics/statistics";
	}
    
	/**
             * 获取饼状图数据
     */
   
	@RequestMapping(value="/echartsData")
    @ResponseBody
    public List<Map<String, Object>> initChart(){
        return ahualyservice.pieData();
    }
	
	 /**
	    * 员工人数柱状图
	  */
	@RequestMapping(value="/echartsData1")
	@ResponseBody
	public List<Map<String, Object>> initChart1(){
	     return ahualyservice.pieData1();
	 }
	
	/**
	 *  一周业务折线图 
	 */
	
	@RequestMapping(value="/echartsData2")
	@ResponseBody
	public List<Map<String, Object>> initChart2(){
	     return ahualyservice.pieData2();
	 }
	
	/**
	 *  地图 
	 */
	@RequestMapping(value="/echartsData3")
	@ResponseBody
	public Map<String, Integer> initChart3(){
	     return ahualyservice.pieData3();
	 }
}
