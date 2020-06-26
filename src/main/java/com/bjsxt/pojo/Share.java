package com.bjsxt.pojo;

public class Share {
//拼车服务
	private String uid; //用户id
	private String mapStart; //上车位置
	private String mapEnd; //目的地位置
	private Integer peopleNum; //拼车人数
	private String QQ; //联系QQ
	private String remark; //备注
	private String bid; //订单号
	private String getUpTime; //上车时间
	private String fettle;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	private String openId;
	public String getFettle() {
		return fettle;
	}
	public void setFettle(String fettle) {
		this.fettle = fettle;
	}
	public String getGetUpTime() {
		return getUpTime;
	}
	public void setGetUpTime(String getUpTime) {
		this.getUpTime = getUpTime;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getMapStart() {
		return mapStart;
	}
	public void setMapStart(String mapStart) {
		this.mapStart = mapStart;
	}
	public String getMapEnd() {
		return mapEnd;
	}
	public void setMapEnd(String mapEnd) {
		this.mapEnd = mapEnd;
	}
	public Integer getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	@Override
	public String toString() {
		return "Share [uid=" + uid + ", mapStart=" + mapStart + ", mapEnd=" + mapEnd + ", peopleNum=" + peopleNum
				+ ", QQ=" + QQ + ", remark=" + remark + ", bid=" + bid + ", getUpTime=" + getUpTime + ", fettle="
				+ fettle + ", openId=" + openId + "]";
	}

	
}
