package com.aboutyun.model;

import java.io.Serializable;
import java.util.Date;

public class Statistical implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ipAddress ;			//IP地址
	private Integer visitNum;			//访问字数
	private Date time;					//访问日期
	private String rate;
	private String searchWod;			//搜索关键字
	private String searchNum;			//搜索次数
	private Integer type;				
	
	private String rowKey;				//行健
	private String url;					//url
	private String userBrowser;			//浏览器类型
	private String os;					//操作系统
	private String accressTime;			//访问时间
	
	public String getRowKey() {
		return rowKey;
	}
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserBrowser() {
		return userBrowser;
	}
	public void setUserBrowser(String userBrowser) {
		this.userBrowser = userBrowser;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getAccressTime() {
		return accressTime;
	}
	public void setAccressTime(String accressTime) {
		this.accressTime = accressTime;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Integer getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getSearchWod() {
		return searchWod;
	}
	public void setSearchWod(String searchWod) {
		this.searchWod = searchWod;
	}
	public String getSearchNum() {
		return searchNum;
	}
	public void setSearchNum(String searchNum) {
		this.searchNum = searchNum;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
