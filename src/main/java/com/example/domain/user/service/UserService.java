package com.example.domain.user.service;

import java.util.List;

import com.example.domain.user.model.MUser;

public interface UserService {//UserServiceImplに渡すためのクラス

	/**ユーザー登録*/
	public void signup(MUser user);//ユーザー登録をするメソッド。引数は登録したいユーザー情報のMUser user
	
	/**ユーザー取得*/
	public List<MUser> getUsers(MUser user);//複数けん取得検索するためlist
	
	/**ユーザー取得（1件）*/
	public MUser getUserOne(String userId);//userIdから欲しい情報だけを出させる仕組み
	
	/**ユーザー更新（1件）*/
	public void updateUserOne(String userId, String password, String userName);//MUserやuserIDは関係なく、更新したいマイ用を描きそれだけ渡す
	
	/**ユーザー削除（1件）*/
	public void deleteUserOne(String userId);//userIdで削除する
	
	/**ログインユーザー情報取得*/
	public MUser getLoginUser(String userId);//上に同じ
}
