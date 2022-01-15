package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Question;
import com.example.demo.model.SiteUser;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.SiteUserRepository;
import com.example.demo.service.PostquestionService;
import com.example.demo.util.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	private final SiteUserRepository userRepository;
	private final QuestionRepository questionRepository;
	private final PostquestionService postquestionService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("user") SiteUser user) {
		return "register";
	}
	
	@PostMapping("/register")
	public String process(@Validated @ModelAttribute("user") SiteUser user, BindingResult result) {
		if(result.hasErrors()) {
			return "register";
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER.name());
		
		userRepository.save(user);
		
		return "redirect:/login?register";
	}
	
	@GetMapping("/postquestion")
	public String getPostquestion(@ModelAttribute("question") Question question) {
		return "postquestion";
	}
	
	@PostMapping("/postquestion")
	public String postquestion(Principal principal, @Validated @ModelAttribute("question") Question question, BindingResult result) {
		if(result.hasErrors()) {
			return "postquestion";
		}
		
		SiteUser currentUser = postquestionService.findUserByUsername(principal.getName())
				.orElseThrow();
		
		question.setUserid(currentUser.getUserid());
		question.setRegistrationTime(LocalDateTime.now());
		
		questionRepository.save(question);
		
		return "index";
	}
}
