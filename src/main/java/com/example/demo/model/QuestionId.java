package com.example.demo.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Questionモデルの主キークラス
 * @author Kanagawa Tatsuki
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userid;
	
	private String isbn;
	
	private Long questionId;
}
