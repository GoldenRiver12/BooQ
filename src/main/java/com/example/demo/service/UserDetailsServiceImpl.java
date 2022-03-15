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
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<SiteUser> user = userRepository.findByUserName(userName);

		return user
				.map(u -> new User(u.getUserName(), u.getPassword(), AuthorityUtils.createAuthorityList(u.getRole())))
				.orElseThrow(() -> new UsernameNotFoundException(String.format("%s not found", userName)));

	}

}
