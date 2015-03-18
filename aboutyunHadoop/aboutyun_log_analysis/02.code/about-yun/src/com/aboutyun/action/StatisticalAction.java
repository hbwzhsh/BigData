package com.aboutyun.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aboutyun.model.Statistical;
import com.aboutyun.service.StatisticalService;

/*
 * 统计的action,所有的action都写在这个类里
 * 
 */
@Controller
@RequestMapping("/statistical")
public class StatisticalAction{
	
	@Autowired
	private StatisticalService statisticalService;
	
	/**
	 * 进入统计首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index.html")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "index";
	}
	/**
	 * 显示日志信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/show_log.html")
	public String showLog(HttpServletRequest request,HttpServletResponse response){
		
		try{
			List<Statistical> statistical=statisticalService.searchLog();
			request.setAttribute("statistical", statistical);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "show_log";
	}
	/**
	 * IP统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ip_statistical.html")
	public String ipStatistical(HttpServletRequest request,HttpServletResponse response) {
		List<Statistical> statistical=statisticalService.ipStatistical();
		request.setAttribute("statistical", statistical);
		return "ip_statistical";
	}
	
	/**
	 * 跳出率统计
	 * 
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/bounceRate_Statistical.html")
	public String bounceRateStatistical(HttpServletRequest request,HttpServletResponse response) {
		List<Statistical> statistical=statisticalService.bounceRateStatistical();
		request.setAttribute("statistical", statistical);
		return "bounceRate_statistical";
	}
	
	/**
	 * 后台登陆IP统计
	 * 
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/backgroundIP_Statistical.html")
	public String backgroundIPStatistical(HttpServletRequest request,HttpServletResponse response) {
		List<Statistical> statistical=statisticalService.backgroundIPStatistical();
		request.setAttribute("statistical", statistical);
		return "backgroundIP_statistical";
	}
	
	/**
	 * 搜索量和搜索词
	 * 
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/searchWord_Statistical.html")
	public String searchWordStatistical(HttpServletRequest request,HttpServletResponse response) {
		List<Statistical> statistical=statisticalService.SearchStatistical();
		request.setAttribute("statistical", statistical);
		return "searchWord_statistical";
	}
	
	/**
	 * 模块点击量
	 * 
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/moduleClicks_Statistical.html")
	public String moduleClicksStatistical(HttpServletRequest request,HttpServletResponse response) {
		List<Statistical> statistical=statisticalService.moduleStatistical();
		request.setAttribute("statistical", statistical);
		return "moduleClicks_statistical";
	}
	
	/**
	 * 导航点击量
	 * 
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/navigationClicks_Statistical.html")
	public String navigationClicksStatistical(HttpServletRequest request,HttpServletResponse response) {
		List<Statistical> statistical=statisticalService.navigationStatistical();
		request.setAttribute("statistical", statistical);
		return "navigation_statistical";
	}
	
}
