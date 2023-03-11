package com.blogappbyprateek.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogappbyprateek.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	//security ke time call hoga
	Optional<User> findByEmail(String email);

}
