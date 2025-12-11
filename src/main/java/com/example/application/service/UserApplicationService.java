package com.example.application.service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

	@Autowired
	private MessageSource messageSource;//MssageSourceはmessage.propertiesからキーを元に文字列を取り出す為の仕組み
	
	/**性別のMapを生成する*/
	public Map<String, Integer> getGenderMap(){//<String, Integer>なのは画面に表示する文字列がキーでDBに登録するのが文字列だから
		Map<String, Integer> genderMap = new LinkedHashMap<>();//性別を入れるためのMapを作成。LinkedHashMapを使う理由は性別の順番を保持しとくため。
		String male = messageSource.getMessage("male", null, Locale.JAPAN);//maleキーに男性をセット、第二因数は必要ないためnullを入れている、locateをJAPANに入力しているため日本語メッセージとなる（male→男性）
		String female = messageSource.getMessage("female", null, Locale.JAPAN);//上に同じ

		genderMap.put(male,1);//maleに値「１」をセット
		genderMap.put(female,2);//femaleに「２」をセット
		return genderMap;//Map型として返す
	
	}
}