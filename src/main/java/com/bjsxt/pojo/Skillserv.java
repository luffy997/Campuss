package com.bjsxt.pojo;

public class Skillserv {
	//技能发布
	private String uid; //用户id
	private String content; //服务内容
	private String remark; //备注信息
	private Integer price; //预计价格
	private Integer servType; //0设计，1编程，2游戏，3其他
	private String  contact; //联系电话
	private String QQ; //联系QQ
	private String bid; //订单号
	private String fettle;
	
	private String openId;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getFettle() {
		return fettle;
	}
	public void setFettle(String fettle) {
		this.fettle = fettle;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getServType() {
		return servType;
	}
	public void setServType(Integer servType) {
		this.servType = servType;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	@Override
	public String toString() {
		return "Skillserv [uid=" + uid + ", content=" + content + ", remark=" + remark + ", price=" + price
				+ ", servType=" + servType + ", contact=" + contact + ", QQ=" + QQ + ", bid=" + bid + ", fettle="
				+ fettle + ", openId=" + openId + "]";
	}

}
