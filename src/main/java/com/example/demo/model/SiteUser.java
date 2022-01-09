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
public class SiteUser {
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
	 * パスワード
	 */
	private String password;
	
	/**
	 * メールアドレス
	 */
	@Email
	private String email;

	/**
	 * ロール
	 */
	private String role;
}
