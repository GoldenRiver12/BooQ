package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.SiteUser;
import com.example.demo.repository.SiteUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostquestionService {
	
	private final SiteUserRepository siteUserRepostory;
	
	public Optional<SiteUser> findUserByUsername(String name) {
		return siteUserRepostory.findByUsername(name);
	}
}
