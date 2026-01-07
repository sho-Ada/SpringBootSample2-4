package com.example.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ErrorAspect {
	
	@AfterThrowing(value = "execution(* com.example..*(..)) &&" //@AfterThrowingは指定した条件に当てはまるメソッドで 例外が投げられたときに、この後のメソッドを実行する。com.example 配下（..）と（戻り値 *、引数 (..)）の全クラスの全メソッドが対象。
	+ "(bean(*Controller) || bean(*Service) || bean(*Repository))" , throwing = "ex")//SpringのBean名がController、Service、Repositoryのものだけにする条件。
	public void throwingNull(DataAccessException ex) {//例外が投げられたときに実行されるメソッド。受け取る例外型がDataAccessExceptionなので、DB系の例外だけを拾う例外。
		//例外処理の内容（ログ出力）
		log.error("DataAccessExceptionが発生しました");//logを残す
	}
}