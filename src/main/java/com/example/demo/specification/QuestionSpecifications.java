package com.example.demo.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Question;



public class QuestionSpecifications {
	public Specification<Question> contentContains(String keyword){
		return (root, query, builder) ->
			keyword.isEmpty() ?
			builder.conjunction() :
			builder.like(root.get("content"), "%" + keyword + "%");
	}
}
