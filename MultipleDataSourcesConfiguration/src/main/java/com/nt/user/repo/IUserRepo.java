package com.nt.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.user.entity.User;

public interface IUserRepo extends JpaRepository<User, Integer> {

}
