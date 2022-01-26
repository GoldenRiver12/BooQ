package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.example.demo.validator.UniqueUsername;

import lombok.*;

/**
 * ユーザーのモデル
 * @author Kanagawa Tatsuki
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @UniqueUsername
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
