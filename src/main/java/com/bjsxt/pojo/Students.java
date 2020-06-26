package com.bjsxt.pojo;

public class Students {

	private Integer id;//编号
	private String sno;//学号
	private String sname;//姓名
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	@Override
	public String toString() {
		return "Students [id=" + id + ", sno=" + sno + ", sname=" + sname + "]";
	}
	
}
