package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{
	public List<Answer> findByQuestionQuestionId(Long questionId);
	public List<Answer> findByQuestionQuestionIdAndRespondentUserId(Long questionId, Long userId);
	public Integer countByQuestionQuestionId(Long questionId);
}
