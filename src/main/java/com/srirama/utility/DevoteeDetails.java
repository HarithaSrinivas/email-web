package com.srirama.utility;


import org.springframework.stereotype.Component;

@Component
public class DevoteeDetails {
	private String devoteeName;
	private String spouseName;
	private int kidNumber;
	private String primaryEmailID;
	private String secondaryEmailID;

	public DevoteeDetails()
	{

	}
	public String getDevoteeName() {
		return devoteeName;
	}

	public void setDevoteeName(String devoteeName) {
		this.devoteeName = devoteeName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public int getKidNumber() {
		return kidNumber;
	}

	public void setKidNumber(int kidNumber) {
		this.kidNumber = kidNumber;
	}
	
	public String getPrimaryEmailID() {
		return primaryEmailID;
	}
	
	public void setPrimaryEmailID(String primaryEmailID) {
		this.primaryEmailID = primaryEmailID;
	}
	
	public String getSecondaryEmailID() {
		return secondaryEmailID;
	}
	
	public void setSecondaryEmailID(String secondaryEmailID) {
		this.secondaryEmailID = secondaryEmailID;
	}

	@Override
	public String toString() {
		return "DevoteeDetails [devoteeName=" + devoteeName + ", spouseName=" + spouseName + ", kidNumber=" + kidNumber
				+ ", emailID1=" + primaryEmailID +", emailID2=" + secondaryEmailID + "]";
	}
	

}
