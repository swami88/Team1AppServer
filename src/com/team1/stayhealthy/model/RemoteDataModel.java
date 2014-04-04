package com.team1.stayhealthy.model;

import java.util.ArrayList;

/**
 * 
 * @author terry
 * Example String:
 * {"eventdata":[{"content":"this is history record0","type":"Note","timeStamp":"Apr 3, 2014 4:14:51 PM","title":"Reocrd","miss":true}],"healthdata":[{"timestamp":"Apr 3, 2014 3:00:00 PM","isSleep":true,"bpl":44.083248215046595,"hr":112.31969963683281,"bph":72.97899392979157,"sleep":40.0,"act":158.56415899279042},{"timestamp":"Apr 3, 2014 4:00:00 PM","isSleep":true,"bpl":62.83505949207043,"hr":110.37898983798507,"bph":123.99617833111589,"sleep":40.0,"act":59.06571353384917}],"ownerEmail":"terry@gmail.com"}
 */


public class RemoteDataModel {
	private String ownerEmail;
	private ArrayList<ChartPointModel> healthdata;
	private ArrayList<RecordModel> eventdata;


	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public ArrayList<ChartPointModel> getHealthdata() {
		return healthdata;
	}

	public void setHealthdata(ArrayList<ChartPointModel> healthdata) {
		this.healthdata = healthdata;
	}

	public ArrayList<RecordModel> getEventdata() {
		return eventdata;
	}

	public void setEventdata(ArrayList<RecordModel> eventdata) {
		this.eventdata = eventdata;
	}

}
