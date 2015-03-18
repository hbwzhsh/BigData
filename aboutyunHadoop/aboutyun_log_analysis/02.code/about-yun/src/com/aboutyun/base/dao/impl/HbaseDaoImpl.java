package com.aboutyun.base.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import com.aboutyun.base.dao.HbaseDao;
import com.aboutyun.model.Statistical;

public class HbaseDaoImpl implements HbaseDao{
	
	private static Configuration conf=null;
	
	static{
		Configuration hbaseconf=new Configuration();
		hbaseconf.set("hbase.zookeeper.quorum", "hadoop");
		conf=HBaseConfiguration.create(hbaseconf);
	}
	
	public Statistical searchByRowKey(String tableName)throws Exception{
		Statistical statistical=new Statistical();
		HTable table = new HTable(conf,tableName); 
		Get get=new Get(Bytes.toBytes("localhost2"));
		Result r=table.get(get);
        statistical.setRowKey(new String(r.getRow()));
        statistical.setIpAddress(new String(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("ipaddress"))));
        statistical.setUrl(new String(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("url"))));
        statistical.setOs(new String(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("os"))));
        statistical.setUserBrowser(new String(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("userbrowser"))));
        statistical.setAccressTime(new String(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("accresstime"))));
        table.close();
		return statistical;
	}
	
    public List<Statistical> QueryAll(String tableName)throws Exception { 
    	List<Statistical> list=new ArrayList();
        HTable table = new HTable(conf,tableName); 
        Scan scan=new Scan();
        scan.setBatch(3);
        ResultScanner rs = table.getScanner(scan); 
        try { 
            for (Result r : rs) { 
                Statistical statistical=new Statistical();
                statistical.setRowKey(new String(r.getRow()));
                statistical.setIpAddress(new String(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("ipaddress"))));
                statistical.setUrl(new String(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("url"))));
                statistical.setOs(new String(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("os"))));
                statistical.setUserBrowser(new String(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("userbrowser"))));
                statistical.setAccressTime(new String(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("accresstime"))));
                list.add(statistical);
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally{
        	if(rs!=null){
        		rs.close();
        	}
        }
        table.close();
        return list;
    } 
	
	public void insertData(String tableName)throws Exception{
		HTable table=new HTable(conf,tableName);
		Put put=new Put("127.0.0.4".getBytes());
		put.add("info".getBytes(), "ipaddress".getBytes(), "127.0.0.2".getBytes());
		put.add("info".getBytes(), "url".getBytes(), "www.aboutyun.com1".getBytes());
		put.add("info".getBytes(), "userbrowser".getBytes(), "IE8".getBytes());
		put.add("info".getBytes(), "os".getBytes(), "windows".getBytes());
		put.add("info".getBytes(), "accresstime".getBytes(), "2014-07-27".getBytes());
		table.put(put);
		table.close();
	}

	@Override
	public void deleteData(String tableName) throws Exception {
		byte[] rowKey=Bytes.toBytes("localhost2");
		Delete delete=new Delete(rowKey);
		HTable table=new HTable(conf,tableName);
		table.delete(delete);
		table.close();
		
	}
	
	public static void main(String args[])throws Exception{
		HbaseDao hBaseDao=new HbaseDaoImpl();
		hBaseDao.insertData("LogTable");
//		List<Statistical> bb=hBaseDao.QueryAll("LogTable");
//		System.out.println(bb.size());
//		for(Statistical cc:bb){
//			System.out.println(cc.getIpAddress());
//		}
//		hBaseDao.searchByRowKey("LogTable");
	}
}
