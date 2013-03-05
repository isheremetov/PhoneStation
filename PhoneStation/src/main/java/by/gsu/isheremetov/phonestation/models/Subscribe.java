package by.gsu.isheremetov.phonestation.models;

import java.util.Date;

public class Subscribe {
    private int id;
    private int userid;
    private int serviceid;
    private Date activationDate;

    public Subscribe(int id, int userid, int serviceid, Date activationDate) {
	super();
	this.id = id;
	this.userid = userid;
	this.serviceid = serviceid;
	this.activationDate = activationDate;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getUserid() {
	return userid;
    }

    public void setUserid(int userid) {
	this.userid = userid;
    }

    public int getServiceid() {
	return serviceid;
    }

    public void setServiceid(int serviceid) {
	this.serviceid = serviceid;
    }

    public Date getActivationDate() {
	return activationDate;
    }

    public void setActivationDate(Date activationDate) {
	this.activationDate = activationDate;
    }

}
