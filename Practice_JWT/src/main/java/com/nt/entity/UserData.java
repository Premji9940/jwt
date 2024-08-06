package com.nt.entity;

import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="User_Informationo")
public class UserData {
	@Id
	@GeneratedValue
	
	private Integer id;
	private String name;
	private String username;
	private String pwd;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> roles;
	public UserData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserData(Integer id, String name, String username, String pwd, Set<String> roles) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.pwd = pwd;
		this.roles = roles;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserData [id=" + id + ", name=" + name + ", username=" + username + ", pwd=" + pwd + ", roles=" + roles
				+ "]";
	}
	

}
