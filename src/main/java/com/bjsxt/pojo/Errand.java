package com.bjsxt.pojo;

public class Errand {
//代拿快递

	private String uid; //用户id
	private Integer errandType;//0代拿快递、1代买东西、2其他
	private String remark;//备注
	private String sendLocation;//送达地点
	private Integer price;//预计价格
	private String  contact;//联系电话
	private String  QQ;//联系QQ
	private String  bid;//订单号
	private String weight;//件数及重量
	
	//存放openId
	private String openId;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	private String fettle; //状态
	public String getFettle() {
		return fettle;
	}
	public void setFettle(String fettle) {
		this.fettle = fettle;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSendLocation() {
		return sendLocation;
	}
	public void setSendLocation(String sendLocation) {
		this.sendLocation = sendLocation;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
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
		return "Errand [uid=" + uid + ", errandType=" + errandType + ", remark=" + remark + ", sendLocation="
				+ sendLocation + ", price=" + price + ", contact=" + contact + ", QQ=" + QQ + ", bid=" + bid
				+ ", weight=" + weight + ", openId=" + openId + ", fettle=" + fettle + "]";
	}

	
}
