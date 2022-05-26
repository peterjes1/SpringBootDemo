package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eId;
	private String eName;
	private String eDesignation;
	private String eMail;
	private String location;
	
	public Employee(int eId, String eName, String eDesignation, String eMail,String location, String activeYN) {
		super();
		this.eId = eId;
		this.eName = eName;
		this.eDesignation = eDesignation;
		this.eMail = eMail;
		this.location = location;
		this.activeYN = activeYN;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	@ColumnDefault("'Y'")
	private String activeYN;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Employee(String eName, String eDesignation, String eMail,String location) {
		super();
		this.eName = eName;
		this.eDesignation = eDesignation;
		this.eMail = eMail;
		this.location = location;
	}

	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", eName=" + eName + ", eDesignation=" + eDesignation + ", activeYN=" + activeYN
				+ ", eMail=" +eMail+"location="+location+"]";
	}

	public int geteId() {
		return eId;
	}
	
	public String getEmail() {
		return eMail;
	}
	
	public String getLocation() {
		return location;
	}
	 
	public void setLocation(String location) {
		this.location = location;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteDesignation() {
		return eDesignation;
	}

	public void seteDesignation(String eDesignation) {
		this.eDesignation = eDesignation;
	}

	public String getActiveYN() {
		return activeYN;
	}

	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}

	
	
	

}
