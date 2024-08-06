package com.nt.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.entity.UserData;
import com.nt.repo.IUserRepo;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepo repo;

//	@Autowired
	private PasswordEncoder encoder=new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("from service of loadUserByUserName "+username);
		Optional<UserData> userdata = getUserDataByName(username);
		System.out.println("conformation "+userdata);
		if (userdata.isEmpty())
			throw new IllegalArgumentException("No Data found");

		UserData user = userdata.get();
		User u = new User(user.getUsername(), user.getPwd(),
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));

		return u;
	}

	// 1.save the user data in DB
	@Override
	public boolean save(UserData data) {
		// encoding the password
		data.setPwd(encoder.encode(data.getPwd()));
		UserData save = repo.save(data);
		return (save == null) ? false : true;
	}

	@Override
	public Optional<UserData> getUserDataByName(String name) {
		return repo.findByUsername(name);
	}

}
