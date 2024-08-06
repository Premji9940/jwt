package com.nt.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nt.entity.UserData;

public interface IUserService extends UserDetailsService {
	
	boolean save(UserData data);
	
	Optional<UserData> getUserDataByName(String name);

}
