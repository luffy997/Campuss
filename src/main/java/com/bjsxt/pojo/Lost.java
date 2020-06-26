package com.bjsxt.pojo;

public class Lost {
	//方便联合查询
	private String nickName;
	private String	avatarUrl;
	
	private Integer id; //编号
	private String uid;
	private Integer releaseType;
	
	private String[] image;
	
	public String[] getImage() {
		return image;
	}
	public void setImage(String[] image) {
		this.image = image;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	private String releaseTime;
	private String content;
	private String images;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getReleaseType() {
		return releaseType;
	}
	public void setReleaseType(Integer releaseType) {
		this.releaseType = releaseType;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Lost [nickName=" + nickName + ", avatarUrl=" + avatarUrl + ", id=" + id + ", uid=" + uid
				+ ", releaseType=" + releaseType + ", releaseTime=" + releaseTime + ", content=" + content + ", images="
				+ images + "]";
	}

}
