package com.bjsxt.pojo;

public class Orders {
	private String bid;
	private String fettle;
	private String startTime;
	private String finishTime;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String receiver;
	private String receiveTime;
	
	//为显示个人订单创建的实体
	//errand
	private String uid;
	private Integer errandType;
	private String  sendLocation;
	private String weight;	
	
	//market
	private String name;
	private String describes;
	private String images;
	private Integer nowPrice;
	private Integer  beforePrice;
	private Integer goodsType;
	private Integer orderType;
	
	//share
	private String mapStart;
	private String mapEnd;
	private Integer peopleNum;
	private String getUpTime;
	
	//skillServ
	private String content;
	
	//users
	private String nickName;
	private int gender;  //性别 1为男  2为女
	private String avatarUrl;
	private String email;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getErrandType() {
		return errandType;
	}
	public void setErrandType(Integer errandType) {
		this.errandType = errandType;
	}
	public String getSendLocation() {
		return sendLocation;
	}
	public void setSendLocation(String sendLocation) {
		this.sendLocation = sendLocation;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public Integer getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(Integer nowPrice) {
		this.nowPrice = nowPrice;
	}
	public Integer getBeforePrice() {
		return beforePrice;
	}
	public void setBeforePrice(Integer beforePrice) {
		this.beforePrice = beforePrice;
	}
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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
	public String getGetUpTime() {
		return getUpTime;
	}
	public void setGetUpTime(String getUpTime) {
		this.getUpTime = getUpTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getServType() {
		return servType;
	}
	public void setServType(Integer servType) {
		this.servType = servType;
	}
	private Integer servType;
	
	
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getFettle() {
		return fettle;
	}
	public void setFettle(String fettle) {
		this.fettle = fettle;
	}
	@Override
	public String toString() {
		return "Orders [bid=" + bid + ", fettle=" + fettle + ", startTime=" + startTime + ", finishTime=" + finishTime
				+ ", receiver=" + receiver + ", receiveTime=" + receiveTime + ", uid=" + uid + ", errandType="
				+ errandType + ", sendLocation=" + sendLocation + ", weight=" + weight + ", name=" + name
				+ ", describes=" + describes + ", images=" + images + ", nowPrice=" + nowPrice + ", beforePrice="
				+ beforePrice + ", goodsType=" + goodsType + ", orderType=" + orderType + ", mapStart=" + mapStart
				+ ", mapEnd=" + mapEnd + ", peopleNum=" + peopleNum + ", getUpTime=" + getUpTime + ", content="
				+ content + ", nickName=" + nickName + ", gender=" + gender + ", avatarUrl=" + avatarUrl + ", email="
				+ email + ", servType=" + servType + "]";
	}




}
