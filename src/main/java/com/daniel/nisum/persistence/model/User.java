package com.daniel.nisum.persistence.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String token;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Phone> phones;
	
	@CreatedDate
	@CreationTimestamp
	private LocalDateTime created;
	
	@LastModifiedDate
	@UpdateTimestamp
	private LocalDateTime modified;
	
	private Boolean isActive = true;

	@CreationTimestamp
	private LocalDateTime lastLogin;
}
