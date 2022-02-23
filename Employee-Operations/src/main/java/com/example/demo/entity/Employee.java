package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
public class Employee {
	
	@Id
	@GeneratedValue
	private int eId;
	private String eName;
	private String eDesignation;
	
	@ColumnDefault("'Y'")
	private String activeYN;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int eId, String eName, String eDesignation, String activeYN) {
		super();
		this.eId = eId;
		this.eName = eName;
		this.eDesignation = eDesignation;
		this.activeYN = activeYN;
	}

	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", eName=" + eName + ", eDesignation=" + eDesignation + ", activeYN=" + activeYN
				+ "]";
	}

	public int geteId() {
		return eId;
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
