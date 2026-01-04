package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper mapper;//データの更新、読取、更新、削除を行うクラス
	
	@Autowired
	private PasswordEncoder encoder;//パスワー度をハッシュ化する為のクラス

	/**ユーザー登録*/
	@Override
	public void signup(MUser user) {//実装クラス
		user.setDepartmentId(1);//部署
		user.setRole("ROLE_GENERAL");//ロール
		
		//パスワードを暗号化
		String rawPassword =user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
		mapper.insertOne(user);
	}
	
	/**ユーザー取得*/
	@Override
	public List<MUser> getUsers(MUser user){//Muserを受け取り複数件返す
		return mapper.findMany(user);//mapperのfindmanyを呼び検索結果をそのまま返す
	} 
	
	/**ユーザー取得（1件）*/
	@Override
	public MUser getUserOne(String userId) {//userIdを受け取り1件返す
		return mapper.findOne(userId);//MapperのfindOneで1件取得して返す
	}
	
	/**ユーザー更新（1件）*/
	@Override
	public void updateUserOne(String userId, String password, String userName) {//更新対象のuserIdと新しいpassword userNameを受け取る
		
		//パスワードを暗号化
		String encryptPassword = encoder.encode(password);//受け取ったパスワードをハッシュ化する
		
		mapper.updateOne(userId, encryptPassword, userName);//mapperのfindmanyを呼びDB更新をする.
		
	}
	
	/**ユーザー削除（1件）*/
	@Override
	public void deleteUserOne(String userId) {//userIdを受け取り削除する。
		int count = mapper.deleteOne(userId);//MapperのdeleteOneを呼びだし削除する。なぜintか？？→戻り値countは削除件数のためにintにしている（0なら対象なし、1なら削除成功など）。
	}
	
	/**ログインユーザー情報取得*/
	@Override
	public MUser getLoginUser(String userId) {//userIdを受け取り、ログインユーザー情報を返す。
		return mapper.findLoginUser(userId);//mapperのfindLoginUserを呼び検索結果をそのまま返す
	}
}
