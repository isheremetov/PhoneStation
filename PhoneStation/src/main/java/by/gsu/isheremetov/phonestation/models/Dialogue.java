package by.gsu.isheremetov.phonestation.models;

import java.util.Date;

public class Dialogue {
    private int id;
    private Date dialogueDate;
    private int duration;
    private String destination;
    private int userId;
    private int serviceId;

    public Dialogue(int id, Date dialogueDate, int duration,
	    String destination, int userId, int serviceId) {
	super();
	this.id = id;
	this.dialogueDate = dialogueDate;
	this.duration = duration;
	this.destination = destination;
	this.userId = userId;
	this.serviceId = serviceId;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Date getDialogueDate() {
	return dialogueDate;
    }

    public void setDialogueDate(Date dialogueDate) {
	this.dialogueDate = dialogueDate;
    }

    public int getDuration() {
	return duration;
    }

    public void setDuration(int duration) {
	this.duration = duration;
    }

    public String getDestination() {
	return destination;
    }

    public void setDestination(String destination) {
	this.destination = destination;
    }

    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    public int getServiceId() {
	return serviceId;
    }

    public void setServiceId(int serviceId) {
	this.serviceId = serviceId;
    }

}
