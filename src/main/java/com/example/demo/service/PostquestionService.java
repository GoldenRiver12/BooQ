package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.SiteUser;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.SiteUserRepository;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostquestionService {
	
	private final SiteUserRepository siteUserRepostory;
	private final BookRepository bookRepository;
	
	public Optional<SiteUser> findUserByUsername(String name) {
		return siteUserRepostory.findByUsername(name);
	}
	
	public List<Book> findBookByKeyword(String keyword) throws IOException{
		return bookRepository.findByKeyword(keyword);
	}
	
	public String findBookByKeywordAsJson(String keyword) throws IOException {
		return new Gson().toJson(findBookByKeyword(keyword));
	}
}
