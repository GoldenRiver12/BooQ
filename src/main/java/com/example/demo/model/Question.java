package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import org.hibernate.validator.constraints.ISBN;

import lombok.*;

/**
 * 質問のモデル
 * @author Kanagawa Tatsuki
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Question implements Serializable{

	private static final long serialVersionUID = 1L;

	
	/**
	 * ユーザーID
	 */
	private Long userId;
	
	/**
	 * 質問対象の本のISBM
	 */
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
	
	@OneToMany(mappedBy = "question")
	private List<Answer> answers;
}
