package com.springboot.bank.bankDataProject.POJO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="swift_all_details")
public class SwiftDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "swift")
	private String swift;
	
	@Column(name ="bank")
	private String bank;
	
	@Column(name="branch")
	private String branch;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="pincode")
	private String pincode;
	
	@Column(name="country")
	private String country;
	
	public SwiftDetails() {}

	public SwiftDetails(int id, String swift, String bank, String branch, String address, String city, String pincode,
			String country) {
		this.id = id;
		this.swift = swift;
		this.bank = bank;
		this.branch = branch;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "SwiftDetails [id=" + id + ", swift=" + swift + ", bank=" + bank + ", branch=" + branch + ", address="
				+ address + ", city=" + city + ", pincode=" + pincode + ", country=" + country + "]";
	}
	
	
}
