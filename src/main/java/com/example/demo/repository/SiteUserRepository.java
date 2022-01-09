package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SiteUser;

public interface SiteUserRepository extends JpaRepository<SiteUser,String>{
	SiteUser findByName(String name);
	boolean existsByName(String name);
}
