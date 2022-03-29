package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Answer;
import com.example.demo.model.Question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SearchQuestionResultDto {
	
	/**
	 * 検索ヒットした質問
	 */
	private Question question;
	
	/**
	 * 検索ヒットした質問に対するログインユーザーの回答一覧
	 */
	private List<Answer> yourAnswers;
}
