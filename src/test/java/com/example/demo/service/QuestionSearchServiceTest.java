package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Book;
import com.example.demo.model.Question;
import com.example.demo.model.SiteUser;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.SiteUserRepository;
import com.example.demo.util.Role;

@SpringBootTest
@Transactional
class QuestionSearchServiceTest {

	@Autowired
	SiteUserRepository siteUserRepository;

	@Autowired
	QuestionRepository questionRepository;

	@MockBean
	BookRepository bookRepository;

	@Autowired
	QuestionSearchService questionSearchService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(bookRepository);
	}

	@Test
	void 質問内容が質問検索キーワードを含み＿本が本検索キーワードを含むとき＿その質問を取得する() throws IOException {
		
		SiteUser user = SiteUser.builder()
			.username("田中 太郎")
			.password("password")
			.email("tanakataro@example.com")
			.role(Role.ROLE_USER.toString())
			.build();

		siteUserRepository.save(user);

		Long userid = siteUserRepository.findByUsername("田中 太郎")
			.get()
			.getUserid();

		Book book = Book.builder()
			.isbn13("9784492470855")
			.thumbnail("http://example.com/test")
			.title("入門熱力学")
			.build();

		Question question = Question.builder()
			.isbn("9784492470855")
			.userid(userid)
			.registrationTime(LocalDateTime.now())
			.content("熱力学の第一法則とは何か？")
			.build();

		
		siteUserRepository.save(user);
		questionRepository.save(question);
		when(bookRepository.findByKeyword("熱力学")).thenReturn(List.of(book));

		
		List<Question> actual = questionSearchService.findQuestionByBookKeywordAndQuestionKeyword("熱力学", "第一法則");
		
		
		assertThat(actual).usingRecursiveComparison()
			.ignoringFields("questionId")
			.ignoringCollectionOrder()
			.isEqualTo(List.of(question));
	}

}
