package com.blogApplication.blogDemo.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.blogDemo.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
 Optional<User> findByEmail(String email);
}
