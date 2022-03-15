package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Book;
import com.example.demo.model.Question;
import com.example.demo.model.SiteUser;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.SiteUserRepository;
import com.example.demo.service.PostquestionService;
import com.example.demo.service.QuestionSearchService;
import com.example.demo.service.SearchquestionService;
import com.example.demo.util.Role;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {

	private final SiteUserRepository userRepository;
	private final QuestionRepository questionRepository;
	private final PostquestionService postquestionService;
	private final SearchquestionService searchquestionService;
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
		if (result.hasErrors()) {
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
	public String postquestion(Principal principal, @Validated @ModelAttribute("question") Question question,
			BindingResult result) {
		if (result.hasErrors()) {
			return "postquestion";
		}

		SiteUser currentUser = postquestionService.findUserByUsername(principal.getName())
			.orElseThrow();

		question.setUserId(currentUser.getUserId());
		question.setRegistrationTime(LocalDateTime.now());

		questionRepository.save(question);

		return "index";
	}

	@GetMapping("/postquestion/booksearch")
	@ResponseBody
	public String bookSearch(@RequestParam("keyword") String keyword) throws IOException {
		return postquestionService.findBookByKeywordAsJson(keyword);
	}

	@GetMapping("/searchquestion")
	public String showSearchquestionPage() {
		return "searchquestion";
	}

	@GetMapping("/searchquestion/search")
	public String searchquestion(Model model, @RequestParam("isbn") String isbn,
			@RequestParam("questionKeyword") String questionKeyword) {
		model.addAttribute("questions", searchquestionService.findQuestionUsingIsbn(isbn, questionKeyword));
		return "searchquestion";
	}

	@GetMapping("/search")
	@ResponseBody
	public String questionSearch(@RequestParam("book-keyword") String bookKeyword,
			@RequestParam("question-keyword") String questionKeyword) {
		return null;
	}

}
