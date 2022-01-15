package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Question;
import com.example.demo.model.QuestionId;

public interface QuestionRepository extends JpaRepository<Question,QuestionId>{

}
