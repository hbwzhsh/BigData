package com.aboutyun.base.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.aboutyun.base.dao.BaseDao;
import com.ibatis.sqlmap.client.SqlMapClient;


public class BaseDaoImpl<T> extends SqlMapClientDaoSupport implements BaseDao{
	
	private SqlMapClientTemplate sqlMapClientTemplate=this.getSqlMapClientTemplate();

	private  final Log logger = LogFactory.getLog("DAO");
	
	public List<T> queryForList(String sqlName) {
		return (List<T>) sqlMapClientTemplate.queryForList(sqlName);
	}

	public List<T> queryForList(String sqlName, Object params) {
		return (List<T>)sqlMapClientTemplate.queryForList(sqlName,params);
	}

	public T queryForObject(String sqlName) {
		return (T)sqlMapClientTemplate.queryForObject(sqlName);
	}

	public T queryForObject(String sqlName, Object params) {
		return (T)sqlMapClientTemplate.queryForObject(sqlName,params);
	}

}
