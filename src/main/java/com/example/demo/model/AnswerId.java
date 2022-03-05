package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Answerモデルの主キークラス
 * @author Kanagawa Tatsuki
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerId implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long answerId;
	
	private Long questionId;
}
