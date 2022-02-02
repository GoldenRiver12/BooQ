package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.Question;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionSearchService {
	private final BookRepository bookRepository;
	private final QuestionRepository questionRepository;

	public List<Question> findQuestionByBookKeywordAndQuestionKeyword(String bookKeyword, String questionKeyword) throws IOException{
		List<String> bookIsbns = bookRepository.findByKeyword(bookKeyword)
				.stream()
				.map(Book::getIsbn13)
				.collect(Collectors.toList());
		return questionRepository.findByIsbmListAndKeyword(bookIsbns, questionKeyword);
	}
}
