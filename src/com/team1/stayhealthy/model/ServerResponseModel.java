package com.team1.stayhealthy.model;

import java.util.ArrayList;
import java.util.Date;

public class ServerResponseModel {

	public enum ResponseType {
		CHECK_REQUEST, 
		//input payload: instance of RemoteRequestModel (ower's email)
		//server check request record to see if exists
		//send back response success, playload instance of RemoteRequestModel
		SEND_REQUEST, 
		//input payload:instance of RemoteRequestModel
		//store this request
		APPROVE_REQUEST, 
		//input payload: instance of RemoteRequestModel with approved: true
		//server: find the instance and set approved:true
		CHECK_REQUEST_STATUS, 
		//inpult payload: instance of RemoteRequestModel (requestor & owner's email)
		//server response either true or false for the approval field
		DOWNLOAD, 
		//input payload: instance of RemoteRequestModel
		//server reads owner and requestor, check if true in server's storage
		//if true, find the remoteDataModel by email (owner's email of input payload)
		//send back response with payload:instance of remotedatamodel
		
		UPLOAD
		//input playload: instance of RemoteDataModel
		//server stores that model by remotedatamodel.getAccocount().getEmail;
		//response OK with on playload
	}

	private Date timestamp;
	private ResponseType type;
	private Boolean successful;
	private String serverMessage;
	private ArrayList<RemoteRequestModel> requests;
	private RemoteDataModel remoteData;

	public Date getTimestamp() {
		return timestamp;
	}

	public String getServerMessage() {
		return serverMessage;
	}

	public void setServerMessage(String serverMessage) {
		this.serverMessage = serverMessage;
	}

	public ArrayList<RemoteRequestModel> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<RemoteRequestModel> requests) {
		this.requests = requests;
	}

	public RemoteDataModel getRemoteData() {
		return remoteData;
	}

	public void setRemoteData(RemoteDataModel remoteData) {
		this.remoteData = remoteData;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public ResponseType getType() {
		return type;
	}

	public void setType(ResponseType type) {
		this.type = type;
	}

	public Boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}



}
