package com.eshopping.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshopping.io.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
