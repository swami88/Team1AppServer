package com.team1.stayhealthy.model;

/**
 * 
 * @author terry
 * Example String:{"requestorName":"swami","ownerEmail":"terry@gmail.com","ownerName":"terry","requestorEmail":"swami@gmail.com","approved":false}
 */

public class RemoteRequestModel {
	private String ownerEmail;
	private String requestorEmail;
	private String ownerName;
	private String requestorName;
	private boolean approved;

	public RemoteRequestModel(){
		
	}
	
	public RemoteRequestModel(String ownerEmail, String requestorEmail) {
		super();
		this.ownerEmail = ownerEmail;
		this.requestorEmail = requestorEmail;
		this.approved = false;
	}
	
	public String getKeyValue(){
		return ownerEmail+"-"+requestorEmail;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getRequestorName() {
		return requestorName;
	}

	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
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
