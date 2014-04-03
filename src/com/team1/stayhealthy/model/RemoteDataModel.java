package com.team1.stayhealthy.model;

import java.util.ArrayList;

import com.android.entity.AccountModel;
import com.android.trend.ChartPointModel;
import com.android.trend.RecordModel;
/**
 * 
 * @author terry
 * Example String:
 * {"account":{"weight":"150","username":"username","birthDate":"Aug 22, 1984 12:00:00 AM","password":"password","email":"ben123@gmail.com","name":"Terry Liu","height":"179","insomnia":false,"gender":false,"newUser":false,"cardio":false,"remenber":false,"Hypertension":false,"Diabetes":false},"eventdata":[{"content":"this is history record1","type":"Reminder","timeStamp":"Apr 2, 2014 5:16:56 PM","title":"Reocrd","miss":true}],"healthdata":[{"timestamp":"Apr 2, 2014 4:00:00 PM","isSleep":true,"bpl":73.53523429913682,"hr":79.17601359328428,"bph":103.12648308547405,"sleep":10.0,"act":116.90127646879303},{"timestamp":"Apr 2, 2014 5:00:00 PM","isSleep":true,"bpl":74.69005356685294,"hr":93.9768288749305,"bph":124.20604434234859,"sleep":70.0,"act":75.1365081933751}]}
 */


public class RemoteDataModel {
	private AccountModel account;
	private ArrayList<ChartPointModel> healthdata;
	private ArrayList<RecordModel> eventdata;

	public AccountModel getAccount() {
		return account;
	}

	public void setAccount(AccountModel account) {
		this.account = account;
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
