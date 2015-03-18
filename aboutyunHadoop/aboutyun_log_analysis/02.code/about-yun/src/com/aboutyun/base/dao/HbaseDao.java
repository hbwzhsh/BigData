package com.aboutyun.base.dao;

import java.util.List;

import com.aboutyun.model.Statistical;

public interface HbaseDao {
	//按行健查询
	public Statistical searchByRowKey(String tableName)throws Exception;

	//查询所有
	public List<Statistical> QueryAll(String tableName)throws Exception;
	
	//插入一条数据
	public void insertData(String tableName)throws Exception;
	
	//删除一条数据
	public void deleteData(String tableName)throws Exception;
	
}
