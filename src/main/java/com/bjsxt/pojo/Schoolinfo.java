package com.bjsxt.pojo;

public class Schoolinfo {
	private Integer id; //编号
	private String releaseTime;
	private String title;
	private String brief;
	private String source;
	private String author;
	private String click;
	private String content;
	private String webSite;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getClick() {
		return click;
	}
	public void setClick(String click) {
		this.click = click;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	@Override
	public String toString() {
		return "schoolinfo [id=" + id + ", releaseTime=" + releaseTime + ", title=" + title + ", brief=" + brief
				+ ", source=" + source + ", author=" + author + ", click=" + click + ", content=" + content
				+ ", webSite=" + webSite + "]";
	}
	
}
