package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Question;
import com.example.demo.model.QuestionId;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {
	
	@Query("SELECT q from Question q WHERE isbn IN :isbnlist AND content LIKE CONCAT('%', :keyword, '%')")
	public List<Question> findByIsbmListAndKeyword(@Param("isbnlist") List<String> isbnList, @Param("keyword") String keyword);
}
