package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 質問に対する回答のモデル
 * @author Kanagawa Tatsuki
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@IdClass(AnswerId.class)
public class Answer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 回答ID
	 */
	@Id
	private Long answerId;
	
	/**
	 * 回答したユーザーID
	 */
	private Long userid;
	
	/**
	 * 回答先の質問ID
	 */
	@Id
	@GeneratedValue
	private Long questionId;
	
	/**
	 * 回答の投稿日時
	 */
	private LocalDateTime registrationTime;
	
	/**
	 * 回答内容
	 */
	private String content;
	
	@ManyToOne
	private Question question;
}
