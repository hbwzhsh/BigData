package com.aboutyun.base.dao;

import java.util.List;

public interface BaseDao<T> {
	public List<T> queryForList(String sqlName);
	public List<T> queryForList(String sqlName,Object params);
	public T queryForObject(String sqlName);
	public T queryForObject(String sqlName,Object params);
}
