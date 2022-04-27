package com.tch.dto;

public class BankDTO {

	private String bankName;
	private String type;
	private String city;
	private String zipcode;
	private String state;
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "BankDTO [bankName=" + bankName + ", type=" + type + ", city=" + city + ", zipcode=" + zipcode
				+ ", state=" + state + "]";
	}
	
}
