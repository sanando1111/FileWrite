package com.dev.boot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProcessFeds {

	private String a_file;
	private String b_file;
	@Id
	private int id;
	public String getA_file() {
		return a_file;
	}
	public void setA_file(String a_file) {
		this.a_file = a_file;
	}
	public String getB_file() {
		return b_file;
	}
	public void setB_file(String b_file) {
		this.b_file = b_file;
	}

	
	public ProcessFeds() {
		// TODO Auto-generated constructor stub
	}
	public ProcessFeds(String a_file, String b_file) {
		super();
		this.a_file = a_file;
		this.b_file = b_file;
	}
	
	@Override
	public String toString()
	{
		
		return String.format("Process [a_file=%s, b_file=%s]", a_file,b_file);
	}
}
