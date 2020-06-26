package com.bjsxt.pojo;


public class Markets {
private String uid;  //用户id
private String name; //物品名
private String describes; //物品描述
private String images; //图片Url
private Integer nowPrice; //现价（元）
private Integer beforePrice; //原价（元）
private String contact;  //手机号
private Integer goodsType; //物品类别（0生活用品、1二手书籍、2数码产品、3其他）
private Integer orderType; //交易方式（0线下自提、1快递邮寄）
private String bid;  //订单号（随机生成字母+数字组合）
private String fettle; //状态

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

public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
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
public String getBid() {
	return bid;
}
public void setBid(String bid) {
	this.bid = bid;
}
@Override
public String toString() {
	return "Markets [uid=" + uid + ", name=" + name + ", describes=" + describes + ", images=" + images + ", nowPrice="
			+ nowPrice + ", beforePrice=" + beforePrice + ", contact=" + contact + ", goodsType=" + goodsType
			+ ", orderType=" + orderType + ", bid=" + bid + ", fettle=" + fettle + ", openId=" + openId + "]";
}



}
