package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.SiteUserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	private final SiteUserRepository userRepository;
	
	public UniqueUsernameValidator() {
		this.userRepository = null;
	}
	
	@Autowired
	public UniqueUsernameValidator(SiteUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return userRepository == null || !userRepository.existsByUsername(value);
	}

}
