package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.User;

import java.lang.String;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
	List<User> findByName(String name);
	
}
