package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserListForm;

@Controller
@RequestMapping("/user")
public class UserListController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**ユーザー画面を一覧表示*/
	@GetMapping("/list")
	public String getUserList(@ModelAttribute UserListForm form, Model model) {
		
		//formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);//新しいMUserを作りsignupFormのコピーを作る。
		
		//ユーザー検索
		List<MUser> userList = userService.getUsers(user);//サービスに検索条件userを渡して、ユーザー一覧（検索結果）を取得する。
		
		//modelに登録
		model.addAttribute("userList", userList);//検索結果をModelに入れる。HTML側で ${userList} として一覧表示できるようになる。
		
		//ユーザー一覧画面を表示
		return "user/list";
	}
}