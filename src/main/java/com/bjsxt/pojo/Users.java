package com.bjsxt.pojo;

public class Users {
	private String uid;
	private String nickName;
	private int gender;  //性别 1为男  2为女
	private String avatarUrl;
	private String email;
	private String openId;
	private String sno; //学号
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenid(String openId) {
		this.openId = openId;
	}
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", nickName=" + nickName + ", gender=" + gender + ", avatarUrl=" + avatarUrl
				+ ", email=" + email + ", openId=" + openId + ", sno=" + sno + "]";
	}

	
}
