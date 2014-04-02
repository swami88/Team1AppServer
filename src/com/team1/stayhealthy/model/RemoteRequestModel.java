package com.team1.stayhealthy.model;

/**
 * 
 * @author terry
 * Example String:{"requestorEmail":"swami@gmail.com","ownerEmail":"terry@gmail.com","approved":false}
 */

public class RemoteRequestModel {
	private String ownerEmail;
	private String requestorEmail;
	private boolean approved;

	public RemoteRequestModel(){
		
	}
	
	public RemoteRequestModel(String ownerEmail, String requestorEmail) {
		super();
		this.ownerEmail = ownerEmail;
		this.requestorEmail = requestorEmail;
		this.approved = false;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getRequestorEmail() {
		return requestorEmail;
	}

	public void setRequestorEmail(String requestorEmail) {
		this.requestorEmail = requestorEmail;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

}
