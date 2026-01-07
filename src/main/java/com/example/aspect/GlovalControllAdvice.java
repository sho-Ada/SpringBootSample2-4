package com.example.aspect;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlovalControllAdvice {//Controllerで例外が起きたときに、ここでキャッチしてerror.htmlを表示するためのクラス
	
	
	/**データベース関連の例外処理*/
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {//例外eと画面へ渡すmodelを受け取る。戻り値は表示するビュー名。
		
		//空文字をセット
		model.addAttribute("error", "");//error という名前で空文字をModelに入れる。error.htmlで${error}を表示出来るようにする。
		
		//メッセージをModelに登録
		model.addAttribute("message", "DataAccessExceptionが発生しました");//error.htmlの${message}想定
		
		//HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);//status に 500（内部サーバエラー）を入れる。
		
		return "error";
	}
	
	/**その他の例外処理*/
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {//例外eと画面へ渡すmodelを受け取る。戻り値は表示するビュー名。
		//空文字をセット
		model.addAttribute("error", "");//error という名前で空文字をModelに入れる。error.htmlで${error}を表示出来るようにする。
		
		//メッセージをModelに登録
		model.addAttribute("message", "Exceptionが発生しました");//error.htmlの${message}想定
		
		//HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);//status に 500（内部サーバエラー）を入れる。
		
		return "error";
		
	}
}
