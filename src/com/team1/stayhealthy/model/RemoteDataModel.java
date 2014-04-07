package com.team1.stayhealthy.model;

import java.util.ArrayList;

/**
 * 
 * @author terry
 * Example String:
 * {"eventdata":[],"healthdata":[{"timestamp":"Apr 6, 2014 5:00:00 PM","isSleep":true,"bpl":53.737081378152226,"hr":136.9068996461561,"bph":74.64096246892356,"sleep":40.0,"act":129.67322183162088},{"timestamp":"Apr 6, 2014 6:00:00 PM","isSleep":true,"bpl":43.7320278963267,"hr":91.07173793099241,"bph":105.89780473616122,"sleep":10.0,"act":15.890675579934525}],"ownerEmail":"terry@gmail.com","ownerName":"terry"}
 */


public class RemoteDataModel {
	private String ownerEmail;
	private String ownerName;
	private ArrayList<ChartPointModel> healthdata;
	private ArrayList<RecordModel> eventdata;


	public String getOwnerEmail() {
		return ownerEmail;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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
