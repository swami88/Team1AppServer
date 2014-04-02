package com.team1.stayhealthy.model;

import java.util.Date;

public class ServerResponseModel {

	public enum ResponseType {
		CHECK_REQUEST, SEND_REQUEST, APPROVE_REQUEST, CHECK_REQUEST_STATUS, DOWNLOAD, UPLOAD
	}

	private Date timestamp;
	private ResponseType type;
	private Boolean successful;
	private String jsonPayload;

	public Date getTimestamp() {
		return timestamp;
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

	public String getJsonPayload() {
		return jsonPayload;
	}

	public void setJsonPayload(String jsonPayload) {
		this.jsonPayload = jsonPayload;
	}

}
