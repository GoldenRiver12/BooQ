package com.example.demo.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Question;



public class QuestionSpecifications {
	public static Specification<Question> contentContains(String keyword){
		return (root, query, builder) ->
			keyword.isEmpty() ?
			builder.conjunction() :
			builder.like(root.get("content"), "%" + keyword + "%");
	}
	
	public static Specification<Question> isbnIn(List<String> isbnList){
		return (root, query, builder) ->
			builder.in(root.get("isbn").in(isbnList));
	}
}
