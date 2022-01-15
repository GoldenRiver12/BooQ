package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import org.hibernate.validator.constraints.ISBN;

import lombok.*;

/**
 * 質問のモデル
 * @author Kanagawa Tatsuki
 *
 */
@Data
@EqualsAndHashCode
@Entity
@IdClass(QuestionId.class)
public class Question implements Serializable{

	private static final long serialVersionUID = 1L;

	
	/**
	 * ユーザーID
	 */
	@Id
	private Long userid;
	
	/**
	 * 質問対象の本のISBM
	 */
	@Id
	@ISBN
	private String isbn;
	
	/**
	 * 質問ID
	 */
	@Id
	@GeneratedValue
	private Long questionId;
	
	/**
	 * 質問の投稿日時
	 */
	private LocalDateTime registrationTime;
	
	/**
	 * 質問内容
	 */
	private String content;
}
