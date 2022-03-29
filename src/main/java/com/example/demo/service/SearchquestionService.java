package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchQuestionResultDto;
import com.example.demo.logic.AuthLogic;
import com.example.demo.model.Book;
import com.example.demo.model.Question;
import com.example.demo.model.SiteUser;
import com.example.demo.repository.AnswerRepository;
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

	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	AuthLogic authLogic;

	@Deprecated
	public List<Question> findQuestionUsingIsbn(String isbn, String questionKeyword) {
		return questionRepository.findAll(Specification.where(isbnIn(List.of(isbn)))
			.and(contentContains(questionKeyword)));
	}

	@Deprecated
	public List<Question> findQuestionUsingBookKeyword(String bookKeyword, String questionKeyword) throws IOException {
		List<String> isbnList = bookRepository.findByKeyword(bookKeyword)
			.stream()
			.map(Book::getIsbn13)
			.collect(Collectors.toList());
		return questionRepository.findAll(Specification.where(isbnIn(isbnList))
			.and(contentContains(questionKeyword)));
	}

	public List<SearchQuestionResultDto> searchQuestionsUsingIsbn(String isbn, String questionKeyword) {
		List<Question> foundQuestions = questionRepository.findAll(Specification.where(isbnIn(List.of(isbn)))
			.and(contentContains(questionKeyword)));

		SiteUser user = authLogic.getCurrentUser()
			.orElseThrow();

		return foundQuestions.stream()
			.map(q -> SearchQuestionResultDto.builder()
				.question(q)
				.yourAnswers(answerRepository.findByQuestionQuestionIdAndRespondentUserId(q.getQuestionId(),
						user.getUserId()))
				.build())
			.collect(Collectors.toList());
	}

	public List<SearchQuestionResultDto> searchQuestionsUsingBookKeyword(String bookKeyword, String questionKeyword)
			throws IOException {
		List<String> isbnList = bookRepository.findByKeyword(bookKeyword)
			.stream()
			.map(Book::getIsbn13)
			.collect(Collectors.toList());

		SiteUser user = authLogic.getCurrentUser()
			.orElseThrow();

		List<Question> foundQuestions = questionRepository.findAll(Specification.where(isbnIn(isbnList))
			.and(contentContains(questionKeyword)));
		return foundQuestions.stream()
			.map(q -> SearchQuestionResultDto.builder()
				.question(q)
				.yourAnswers(answerRepository.findByQuestionQuestionIdAndRespondentUserId(q.getQuestionId(),
						user.getUserId()))
				.build())
			.collect(Collectors.toList());
	}
}
