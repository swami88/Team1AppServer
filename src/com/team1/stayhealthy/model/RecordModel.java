package com.team1.stayhealthy.model;

import java.io.Serializable;
import java.util.Date;


public class RecordModel implements Comparable<RecordModel>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3704201746287743976L;
	private String title;
	private String content;
	private Date timeStamp;
	private recordType type;
	private boolean miss;

	public enum recordType implements Serializable {
		Recommendation, Note, Reminder;
	}

	public RecordModel() {
		this.title = "Record";
		this.content = "There is a record example";
		this.timeStamp = new Date();
		this.type = recordType.Recommendation;
		this.miss = true;
	}

	public RecordModel(recordType type, Date date, String content,String title , boolean miss) {
		this.content = content;
		this.title = title;
		this.timeStamp = date;
		this.type = type;
		this.miss = miss;
	}
	
	public boolean isMissed(){
		return miss;
	}

	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public recordType getType() {
		return type;
	}

	public void setType(recordType type) {
		this.type = type;
	}

	@Override
	public int compareTo(RecordModel another) {
		return -(this.timeStamp.compareTo(another.timeStamp));
	}

}
