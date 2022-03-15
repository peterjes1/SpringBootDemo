package com.example.demo.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserDAO;

public interface UserRepository extends JpaRepository<UserDAO, Integer> {

	@Cacheable(cacheNames = {"employees"})
	UserDAO findByUsername(String username);
}
