package user;

import java.util.Date;

public class photocomment {
int wid;
String pid;
String comment;
int score;
Date regdate;
String userid;
String delflag;

public photocomment() {
	
}
public photocomment(int wid, String pid, String comment, int score, Date regdate, String userid, String delflag) {
	this.wid = wid;
	this.pid = pid;
	this.comment = comment;
	this.score = score;
	this.regdate = regdate;
	this.userid = userid;
	this.delflag = delflag;
}
public int getWid() {
	return wid;
}
public void setWid(int wid) {
	this.wid = wid;
}
public String getPid() {
	return pid;
}
public void setPid(String pid) {
	this.pid = pid;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
public Date getRegdate() {
	return regdate;
}
public void setRegdate(Date regdate) {
	this.regdate = regdate;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getDelflag() {
	return delflag;
}
public void setDelflag(String delflag) {
	this.delflag = delflag;
}


}
