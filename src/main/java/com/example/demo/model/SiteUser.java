package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

/**
 * ユーザーのモデル
 * @author Kanagawa Tatsuki
 *
 */
@Data
@Entity
public class User {
	/**
	 * ユーザーID
	 */
	@Id
	private String id;
	
	
	/**
	 * ユーザー名
	 */
	private String name;
	
	/**
	 * メールアドレス
	 */
	@Email
	private String email;
}
