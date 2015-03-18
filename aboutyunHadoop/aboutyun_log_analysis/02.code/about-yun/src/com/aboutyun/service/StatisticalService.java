package com.aboutyun.service;

import java.util.List;

import com.aboutyun.model.Statistical;

public interface StatisticalService {
	
	//查询hbase中日志信息
	public List<Statistical> searchLog()throws Exception;
	
	
	//查询ip统计系统
	public List<Statistical> ipStatistical();
	
	//跳出率统计
	public List<Statistical> bounceRateStatistical();
	
	//后台登陆IP统计
	
	public List<Statistical> backgroundIPStatistical();
	
	//统计搜索词和搜索量
	public List<Statistical> SearchStatistical();
	
	//统计模块点击量
	public List<Statistical> moduleStatistical();
	
	//统计导航点击量
	public List<Statistical> navigationStatistical();
}
