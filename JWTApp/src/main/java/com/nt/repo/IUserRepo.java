package com.nt.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.UserData;

public interface IUserRepo extends JpaRepository<UserData, Integer> {
	
	Optional<UserData> findByUsername(String name);
}
