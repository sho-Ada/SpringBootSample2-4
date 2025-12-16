package com.example.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data//Lombokgetter,setter,toString,equals,hashCodeを書かなくて良くなる
public class SignupForm {

	@NotBlank(groups = ValidGroup1.class)//空欄禁止。（スペースもダメ）
	@Email(groups = ValidGroup2.class)//メール形式っぽいかをチェックValid2の場合チェック。ValidGroup2はなにか聞く
	private String userId;//入力されたIdを保存する為の箱.signupHTMLのth:field="*{userId}"に対応している 

	@NotBlank(groups = ValidGroup1.class)//空欄禁止。（スペースもダメ）
	@Length(min = 4, max = 100, groups = ValidGroup2.class)//文字数が4〜１００文字であるかをチェック
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)//英数字のみになっているかチェック
	private String password;//入力されたpasswordを保存する為の箱.signupHTMLのth:field="*{password}"に対応している 

	@NotBlank(groups = ValidGroup1.class)//空欄禁止。（スペースもダメ）
	private String userName;//入力されたuserNameを保存する為の箱.signupHTMLのth:field="*{userName}"に対応している 

	@DateTimeFormat(pattern = "yyyy/MM/dd")//入力された型が2025/01/01のようになっているかチェック
	@NotNull(groups = ValidGroup1.class)//null禁止。空欄禁止。
	private Date birthday;//入力されたbirthdayを保存する為の箱.signupHTMLのth:field="*{birthday}"に対応している 

	@Min(value = 20, groups = ValidGroup2.class)//年齢が20歳以上かチェック
	@Max(value = 100, groups = ValidGroup2.class)//年齢が100歳以下かチェック
	private Integer age;//入力されたageを保存する為の箱.signupHTMLのth:field="*{age}"に対応している 

	@NotNull(groups = ValidGroup1.class)//null禁止。空欄禁止。
	private Integer gender;//入力されたgenderを保存する為の箱.signupHTMLのth:field="*{gender}"に対応している 
}
