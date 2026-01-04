package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();//パスワードをハッシュ化するエンコーダ-を返す。
	}
	
	/**セキュリティの対象を設定*/
	@Override
	public void configure(WebSecurity web) throws Exception{
		//セキュリティを適用しない
		web
		.ignoring()//ここで指定するパスは Spring Securityのフィルタ自体を通さない
		.antMatchers("/webjars/**")//webjars配下は無視
		.antMatchers("/css/**")//css配下は無視
		.antMatchers("/js/**")//js配下は無視
		.antMatchers("/h2-console/**");//h2-console配下は無視
	}
	
	/**セキュリティの各種設定*/
	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		//ログイン不要ページの設定
		http
		.authorizeRequests()
		.antMatchers("/login").permitAll()//直リンクOK
		.antMatchers("/user/signup").permitAll()//直リンクOK
		.antMatchers("/admin").hasAuthority("ROLE_ADMIN")//権限制御
		.anyRequest().authenticated();//直リンクNG
		
		//ログイン処理
		http
		.formLogin()
		.loginProcessingUrl("/login")//ログイン処理へのパス
		.loginPage("/login")//ログインページの指定
		.failureUrl("/login?error")//ログイン失敗時の遷移先
		.usernameParameter("userId")//ログインページのユーザーID
		.passwordParameter("password")//ログインページのパスワード
		.defaultSuccessUrl("/user/list", true);//成功時の遷移先
		
		//ログアウト処理
		http
		.logout()//ログアウト
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))// /logoutに来たらログアウト扱いにする判定。
		.logoutUrl("/logout")//ログアウトのurl遷移先
		.logoutSuccessUrl("/login?logout");//ログアウト後は「/login?logout」にとぶ。
		
		//CSRF対策を無効に設定（一時的）
		//http.csrf().disable();
	}
	
	/**認証の設定*/
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder encoder = passwordEncoder();//上の@Beanで作ったBCryptエンコーダを取得。
		//インメモリ認証
		/*
		auth
		.inMemoryAuthentication()
		.withUser("user")//userを追加
		.password(encoder.encode("user"))
		.roles("GENERAL")
		.and()
		.withUser("admin")
		.password(encoder.encode("admin"))
		.roles("ADMIN");
		*/
		
		//ユーザーデータで認証
		auth//設定開始のあいず。
		.userDetailsService(userDetailsService)//ユーザー情報はuserDetailsServiceから取得してくる。認証する。
		.passwordEncoder(encoder);//パスワードがあっているかをBCryptで確認、照合する。
	}
	
	
}
