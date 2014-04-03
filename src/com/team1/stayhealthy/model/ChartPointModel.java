package com.team1.stayhealthy.model;

import java.util.Date;

public class ChartPointModel implements Comparable<ChartPointModel>{
	private Date timestamp;
	private double hr;
	private double bpl;
	private double bph;
	private double act;
	private double sleep;
	private boolean isSleep;
	public static final double SLEEP_LOW=10;
	public static final double SLEEP_MED=40;
	public static final double SLEEP_HIGH=70;
	
	
	
	public ChartPointModel(Date timestamp, double hr, double bpl, double bph, double act, double sleep, boolean isSleep) {
		this.timestamp = timestamp;
		this.hr = hr;
		this.bpl = bpl;
		this.bph = bph;
		this.act = act;
		this.sleep = sleep;
		this.isSleep = isSleep;
	}
	
	
	
	public boolean isSleep(){
		return isSleep;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public double getHr() {
		return hr;
	}
	public double getBpl() {
		return bpl;
	}
	public double getBph() {
		return bph;
	}
	public double getAct() {
		return act;
	}
	public double getSleep() {
		return sleep;
	}
	@Override
	public int compareTo(ChartPointModel anotherPoint) {
		return this.timestamp.compareTo(anotherPoint.getTimestamp());
	}
	
	
}
