package com.anis.IslingtonSchedule;

public class DaySchedule {
	
	private String startTime;
	private String endTime;
	private String type;
	private String module;
	private String lecturer;
	private String room;
	
	public DaySchedule(String sT, String eT, String tP, String md, String lec, String rm)
	{
		startTime=sT;
		endTime=eT;
		type=tP;
		module=md;
		lecturer=lec;
		room=rm;
		
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getLecturer() {
		return lecturer;
	}
	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	
	
}
