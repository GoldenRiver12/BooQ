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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    
    /**
     * ユーザー名
     */
    @Column(unique=true)
    private String username;
	
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
