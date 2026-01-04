package com.example.controller;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;
import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")// /userから始まるURLを使用しますよという意味、全ての先頭に/userがつく
@Slf4j
public class SignupController {
	
	@Autowired//DI
	private UserApplicationService userApplicationService;//性別マスタを取得したり画面の共通ロジックをまとめたサービスを使う為のもの
	
	@Autowired
	private UserService userService;//DB登録をしているクラス
	
	@Autowired
	private ModelMapper modelMapper;//MUserの自動コピーする為のもの
	/**ユーザー登録画面を表示*/
	@GetMapping("/signup")
	public String getSignup(Model model, @ModelAttribute SignupForm form) {//Model modelはHTMLに値を渡す為の入れ物
		//性別を取得
		Map<String, Integer> genderMap = userApplicationService.getGenderMap();//ApplicationServiceから性別と値を取得
		model.addAttribute("genderMap", genderMap);//取得したものをmodelに詰める。ここでHTMLから＄{genderMap}として参照できるようになる
		
		//ユーザー登録画面に遷移
		return "user/signup";//user/signupというテキストを返す。（signup画面に戻る）
	}
	
	/**ユーザー登録処理*/
	@PostMapping("/signup")
	public String postSignup(Model model, @ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult) {
		//Model modelは画面に表示する為の箱HTMLから参照できるようにするために作っている。@ModelAttribute SignupForm formでPOSTされた値をSpringがここへ詰める。@Validated(GroupOrder.class)は詰めた後に入力チェックを実行する。BindingResult bindingResultにエラー内容入る
		
		//入力チェック結果
		if(bindingResult.hasErrors()) {//入力チェックでエラーが1つでもあるか？

			return getSignup(model, form);//エラーがあったらユーザー登録画面に戻る。formを渡しているから入力画面に入力値が残りやすい。←なぜ？？
			
		}
		
		log.info(form.toString());//ログを残す
		
		//formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);//新しいMUserを作りsignupFormのコピーを作る。
		
		//ユーザー登録
		userService.signup(user);
		
		//ログイン画面にリダイレクト
		return "redirect:/login";//ログイン画面に戻る
	}
	
	/**データベース関連の例外処理*/
	@ExceptionHandler(DataAccessException.class)//もしDataAccessExceptionが起きたらこのクラスで処理する。
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {//eは起きた例外
		
		//空文字をセット
		model.addAttribute("error", "");
		
		//メッセージをModelに登録
		model.addAttribute("message", "SignupControllerで例外が発生しました");
		
		//HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
	
	/**その他の例外処理*/
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {//上に同じ
		//空文字をセット
		model.addAttribute("error", "");
		
		//メッセージをModelに登録
		model.addAttribute("message", "SignupControllerで例外が発生しました");
		
		//HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
		
	}
	
	
	
	
	
	
	
}
