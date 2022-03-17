package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{
	public List<Answer> findByQuestionQuestionId(String questionId);
	public Integer countByQuestionQuestionId(String questionId);
}
