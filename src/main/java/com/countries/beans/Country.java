package com.countries.beans;

public class Country {
	
	private String capital;
	private String regionId;
	private String incomeLevel;
	private String lendingType;
	private String name;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getIncomeLevel() {
		return incomeLevel;
	}
	public void setIncomeLevel(String incomeLevel) {
		this.incomeLevel = incomeLevel;
	}
	public String getLendingType() {
		return lendingType;
	}
	public void setLendingType(String lendingType) {
		this.lendingType = lendingType;
	}
	
	@Override
	public String toString() {
		return "Countries [capital=" + capital + ", regionId=" + regionId + ", incomeLevel=" + incomeLevel
				+ ", lendingType=" + lendingType + "]";
	}
	
	
	

}
