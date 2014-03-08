package com.team1.stayhealthy.model;

public class User {
	
	String id;
	String recommendations[];
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(String[] recommendations) {
		this.recommendations = recommendations;
	}
	
}
