package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.Question;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.QuestionRepository;
import static com.example.demo.specification.QuestionSpecifications.*;

import org.springframework.data.jpa.domain.Specification;

@Service
public class SearchquestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	BookRepository bookRepository;

	public List<Question> findQuestionUsingIsbn(String isbn, String questionKeyword) {
		return questionRepository.findAll(Specification.where(isbnIn(List.of(isbn)))
			.and(contentContains(questionKeyword)));
	}

	public List<Question> findQuestionUsingBookKeyword(String bookKeyword, String questionKeyword) throws IOException {
		List<String> isbnList = bookRepository.findByKeyword(bookKeyword)
			.stream()
			.map(Book::getIsbn13)
			.collect(Collectors.toList());
		return questionRepository.findAll(Specification.where(isbnIn(isbnList))
			.and(contentContains(questionKeyword)));
	}
}
