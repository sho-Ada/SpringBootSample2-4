package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j//クラス内でロガーというものを使えるようにしている
public class LogoutController {

	/**ログイン画面にリダイレクト*/
	@PostMapping("/logout")
	public String postLogout() {
		log.info("ログアウト");//ログ出力ログレベルINFOでログアウトというメッセージを出す
		return "redirect:/login";//ログイン画面へリダイレクトする
	}
}
