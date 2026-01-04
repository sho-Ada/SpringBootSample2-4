package com.example.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//Springの設定クラスという宣言
public class JavaConfig {

	@Bean//このメソッドが返すオブジェクトを、Beanとして登録するということ
	public ModelMapper modelMapper() {//引数なし
		return new ModelMapper();//ModelMapperのインスタンスを作って返す
	}
}
