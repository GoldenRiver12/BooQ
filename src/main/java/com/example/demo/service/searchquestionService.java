package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Question;

@Service
public class searchquestionService {
	public List<Question> findQuestionByQuestionKeyword(String questionKeyword){
		return List.of();
	}
}
