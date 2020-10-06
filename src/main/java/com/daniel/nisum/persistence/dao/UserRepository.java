package com.daniel.nisum.persistence.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.nisum.persistence.model.User;

public interface UserRepository extends JpaRepository<User, UUID>{

	User findByEmail(String email);

}
