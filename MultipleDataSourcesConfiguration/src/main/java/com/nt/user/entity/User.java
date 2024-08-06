package com.nt.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue
	private Integer uid;
	private String name;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public User(Integer uid, String name) {
		super();
		this.uid = uid;
		this.name = name;
	}


	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + "]";
	}
	

}
