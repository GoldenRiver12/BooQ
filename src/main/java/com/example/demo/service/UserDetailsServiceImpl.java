package com.example.demo.service;

import java.util.Optional;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.SiteUser;
import com.example.demo.repository.SiteUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final SiteUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SiteUser user = userRepository.findByUsername(username);

		return Optional.ofNullable(user)
				.map(u -> new User(u.getUsername(), u.getPassword(), AuthorityUtils.createAuthorityList(u.getRole())))
				.orElseThrow(() -> new UsernameNotFoundException(String.format("%s not found", username)));

	}

}
