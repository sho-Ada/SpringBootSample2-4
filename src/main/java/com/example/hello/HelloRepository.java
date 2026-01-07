package com.example.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository //DataBase操作を表すときにつけるやつ
public class HelloRepository {
	
	@Autowired//依存注入のアノテーション
	private JdbcTemplate jdbcTemplate;//Spring が用意しているJdbcTemplateを使ってSQLを実行する。
	public Map<String,Object> findById(String id){//引数idを元に、1件だけ従業員データを取得して返すメソッドを作りたい
		
		//SELECT文
		String query = "SELECT *" 
		+ " FROM employee"
	    + " WHERE id = ?";//?をつけるとsqlインジェクション対策にもなる←調べてもわからなかった
		
		//検索実行
		Map<String, Object> employee = jdbcTemplate.queryForMap(query,id);//SQLを実行し結果を一件のMapとして登録
		
		return employee;//取得した従業員データをMapとして返す
	}
}
