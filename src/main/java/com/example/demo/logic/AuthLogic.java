package com.example.demo.logic;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.model.SiteUser;
import com.example.demo.repository.SiteUserRepository;

@Component
public class AuthLogic {

	@Autowired
	SiteUserRepository siteUserRepository;

	/**
	 * @return ログイン中のユーザー
	 */
	public Optional<SiteUser> getCurrentUser() {
		Object principal = SecurityContextHolder.getContext()
			.getAuthentication()
			.getPrincipal();
		if (principal instanceof UserDetails) {
			return siteUserRepository.findByUserName(((UserDetails) principal).getUsername());
		} else {
			return Optional.empty();
		}
	}
}
