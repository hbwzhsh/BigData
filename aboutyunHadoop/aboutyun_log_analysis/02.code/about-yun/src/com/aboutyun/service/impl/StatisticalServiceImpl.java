package com.aboutyun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aboutyun.base.dao.BaseDao;
import com.aboutyun.base.dao.HbaseDao;
import com.aboutyun.model.Statistical;
import com.aboutyun.service.StatisticalService;

@Service
public class StatisticalServiceImpl implements StatisticalService{
	
	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private HbaseDao hBaseDao;
	
	/*
	 * 查询hbase中日志信息(non-Javadoc)
	 */
	public List<Statistical> searchLog()throws Exception{
		return hBaseDao.QueryAll("LogTable");
	}

	/*
	 * IP统计
	 */
	public List<Statistical> ipStatistical() {
		return baseDao.queryForList("AboutYunStatistical.queryIp");
	}
	
	/*
	 * 跳出率统计
	 */
	public List<Statistical> bounceRateStatistical() {
		return baseDao.queryForList("AboutYunStatistical.queryBounceRate");
	}
	
	/*
	 * 后台登陆IP统计
	 */
	public List<Statistical> backgroundIPStatistical() {
		return baseDao.queryForList("AboutYunStatistical.queryBackgroundIP");
	}
	
	/*
	 * 统计搜索词和搜索量
	 */
	public List<Statistical> SearchStatistical() {
		return baseDao.queryForList("AboutYunStatistical.querySearchWord");
	}
	
	/*
	 * 统计模块点击量
	 */
	public List<Statistical> moduleStatistical() {
		return baseDao.queryForList("AboutYunStatistical.queryModuleClicks");
	}
	
	/*
	 * 统计导航点击量
	 */
	public List<Statistical> navigationStatistical() {
		return baseDao.queryForList("AboutYunStatistical.queryNavigationClicks");
	}

}
