package com.example.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service//サービスクラスであることの宣言 コントローラーとリポジトリーの間で橋渡しをする役割
public class HelloService {//サービスではリポジトリーを使ってSQLの実行結果を取得するためのクラス
	
	@Autowired//依存性注入
	private HelloRepository repository;//repository
	
	/**従業員を1人取得する*/
	public Employee getEmployee(String id) {//idを取得して１人分の従業員データを取得しアプリ用のEmployeeオブジェクトにして返す
		

		//検索
		Map<String, Object> map = repository.findById(id);//repositoryのfindById(id)を実行し、sqlの検索結果１件をmapに入れたものを受け取る
		
		//Mapから値を取得
		String employeeId = (String) map.get("id");//下2つも同じだがmap.getはobject型なのでIntegerやStringを使う場合にはキャストしなくてはならない
		String name = (String) map.get("name");
		int age = (Integer) map.get("age");
		
		//Employeeクラスに値をセット
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);//empoloyeeオブジェクトに入れていく作業
		employee.setEmployeeName(name);
		employee.setEmployeeAge(age);
		
		return employee;//employeeオブジェクトとして返す
	}
	
}
