package user;

import java.util.Date;

public class photo {
String pid;
String picname;
String content;
String category;
int price;
Date regdate;
String delflag;
String path;


public photo(String pid, String picname, String content, String category, int price, Date regdate, String delflag,
		String path) {
	this.pid = pid;
	this.picname = picname;
	this.content = content;
	this.category = category;
	this.price = price;
	this.regdate = regdate;
	this.delflag = delflag;
	this.path = path;
}
public String getPid() {
	return pid;
}
public void setPid(String pid) {
	this.pid = pid;
}
public String getPicname() {
	return picname;
}
public void setPicname(String picname) {
	this.picname = picname;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public Date getRegdate() {
	return regdate;
}
public void setRegdate(Date regdate) {
	this.regdate = regdate;
}
public String getDelflag() {
	return delflag;
}
public void setDelflag(String delflag) {
	this.delflag = delflag;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
}
