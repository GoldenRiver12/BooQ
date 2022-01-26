package com.example.demo.repostitory;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Question;
import com.example.demo.model.SiteUser;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.SiteUserRepository;
import com.example.demo.util.Role;

@SpringBootTest
@Transactional
class QuestionRepositoryTest {

	@Autowired
	SiteUserRepository siteUserRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Test
	@DisplayName("キーワードを含む質問が存在するとき、その質問を取得することを期待します")
	void whenContentContainsKeyword_expectToGetQuestion() {

		// 質問を登録するユーザーを作成
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

		// 質問（キーワードを含む）
		Question question = Question.builder()
			.userid(userid)
			.isbn("9784492470855")
			.registrationTime(LocalDateTime.now())
			.content("熱力学の第一法則とは何か？")
			.build();

		// 質問（キーワードを含まない）
		Question questionDoNotContainsKeyword = Question.builder()
			.userid(userid)
			.isbn("9784492470855")
			.registrationTime(LocalDateTime.now())
			.content("法律とは何か？")
			.build();

		questionRepository.saveAll(List.of(question, questionDoNotContainsKeyword));

		List<Question> searchedQuestions = questionRepository.findByIsbmListAndKeyword(List.of("9784492470855"),
				"法則");

		// 検索結果が1件であることを確認
		assertEquals(1, searchedQuestions.size());

		Question actual = searchedQuestions.get(0);

		// キーワードを含んだ質問が取得できたことを確認
		assertEquals(question.getUserid(), actual.getUserid());
		assertEquals(question.getIsbn(), actual.getIsbn());
		assertEquals(question.getUserid(), actual.getUserid());
		assertEquals(question.getRegistrationTime(), actual.getRegistrationTime());
		assertEquals(question.getContent(), actual.getContent());
	}

}
