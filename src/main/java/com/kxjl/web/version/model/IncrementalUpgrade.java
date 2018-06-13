package com.kxjl.web.version.model;


public class IncrementalUpgrade {

	private int recordid;  
	private int clienttype;  
	private double currentVersion; 
	private double lowVersion; 
	private String diffPath; 
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public int getClienttype() {
		return clienttype;
	}
	public void setClienttype(int clienttype) {
		this.clienttype = clienttype;
	}
	public double getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(double currentVersion) {
		this.currentVersion = currentVersion;
	}
	public double getLowVersion() {
		return lowVersion;
	}
	public void setLowVersion(double lowVersion) {
		this.lowVersion = lowVersion;
	}
	public String getDiffPath() {
		return diffPath;
	}
	public void setDiffPath(String diffPath) {
		this.diffPath = diffPath;
	}
}
