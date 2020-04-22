package com.app.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.handler.SampleAuthenticationFailureHandler;
import com.app.handler.SampleAuthenticationSuccessHandler;

//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.formLogin()
//		//登録画面
//		.loginPage("/index")
//		//成功の場合、関数を実行
//		.successHandler(null)
//		//失敗の場合、関数を実行
//		.failureHandler(null)
//		//検証の処理
//		.loginProcessingUrl("/user/login")
//		.and()
//		.authorizeRequests()
//		.antMatchers("/user/login").permitAll()
//		.antMatchers("/query").hasAnyAuthority("admin","stu")
//		.anyRequest()
//		.authenticated()
//		.and()
//		.csrf()
//		.disable();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("0000").roles("admin");
//	}
//}
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	private DataSource dataSource;

	// ポイント1
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 静的ファイルなどを置いている場所
		web.ignoring().antMatchers("/bootstrap/**", "/jquery/**");
	}

	// ポイント2
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// CSRF検証の閉じる
		http.csrf().disable();

		// -----------------------------------------方法一----------------------------------------------------
		http.authorizeRequests()
				// 認証不要の画面又はパスを指定
				.antMatchers("/demo/**").permitAll()
				// Admin権限を持つ場合、下記URLにアクセスできる
				.antMatchers("/testpath/test01").hasAnyAuthority("ROLE_ADMIN")
				.anyRequest().authenticated();
		//SpringSecurityの基本の画面を呼び出す
		http.formLogin().and().httpBasic();

		// -----------------------------------------方法二----------------------------------------------------
		// 基本的な設定(検証なし)
		// http.httpBasic().and().csrf().disable();

		// -----------------------------------------方法三----------------------------------------------------
		// 【設定一】
//		http.authorizeRequests()
//				// 認証不要の画面又はパスを指定
//				.antMatchers("/demo/**", "/admin/**").permitAll()
//				// 例
//				.antMatchers("/testpath/test01").hasAnyAuthority("ROLE_ADMIN")
//				// Roleにより、 認証必要の画面を指定("ADMIN"権限がないとアクセスできない)
//				// .antMatchers("/admin/**").hasRole("admin")
//				// 権限(testuser)により、認証必要の画面(/testpath)を指定
//				// .antMatchers("/testpath/test01").hasAnyAuthority("testuser")
//				// 残りの全てのURLリクエストは認証されているユーザーしかアクセスできない
//				.anyRequest().authenticated();

		// 【設定二】
//		// ログイン設定
//		http.formLogin()
//				// ログインフォーム画面(具体的にHTMLの位置)
//				.loginPage("/admin/index")
//				// 認証処理のパス（index.htmlのFormActionと一致する）
//				.loginProcessingUrl("/admin/login")
//				// ログインフォーム画面のhtmlのinputのname属性
//				.usernameParameter("username").passwordParameter("password")
//
//				// 認証成功時の遷移先URL---OK
//				// (訪問URLはJSON値の返却の場合用、URLは「loginProcessingUrl」のままです)
//				// .successForwardUrl("/admin/success")
//				// 認証失敗時の遷移先URL---OK
//				// (訪問URLはJSON値の返却の場合用、URLは「loginProcessingUrl」のままです)
//				// .failureForwardUrl("/admin/errors")
//
//				// 検証成功の場合、引数「true」の場合、下記のURLをずっと呼びます。
//				// .defaultSuccessUrl("/admin/success")
//				// ログインに失敗した時のURL
//				// (ユーザ名又はパスワードがいずれか入力が間違った場合、遷移用)
//				// .failureUrl("/admin/index?error=E0")
//
//				// 認証成功時に呼ばれるハンドラクラス(新規クラス)---OK(forward or redirect)
//				// .successHandler(new SampleAuthenticationSuccessHandler())
//				// 認証失敗時に呼ばれるハンドラクラス(新規クラス)---OK(forward or redirect)
//				// .failureHandler(new SampleAuthenticationFailureHandler())
//
//				.and();

//		// ログアウト設定
//		http.logout()
//				// ログアウトのURL
//				.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout**"))
//				// ログアウト成功したあとのURL
//				.logoutSuccessUrl("/admin/logout")
//				// ログアウトしたら cookieの JSESSIONID を削除
//				.deleteCookies("JSESSIONID")
//				// ログアウトしたらセッションを無効にする
//				.invalidateHttpSession(true).permitAll();
//
//		// 【設定三】
//		http.sessionManagement().invalidSessionUrl("/admin/logout");

		// ---------------------------------------------------------------------------------------------
	}

	// ポイント3
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		// ①簡単型
//		auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("123456"))
//		.authorities("testuser").and().passwordEncoder(passwordEncoder());

		// ②DBより、複雑型
		auth.jdbcAuthentication()
				// DataSource設定
				.dataSource(dataSource)
				// TBL「accounts」のmail_address（username）を検索
				.usersByUsernameQuery("select username, password, enabled from TBL_ACCOUNTS where username = ?")
				// ユーザーの持つ権限の取得
				.authoritiesByUsernameQuery("select username, role from TBL_ACCOUNTS where username = ?")
				// TBLのデータをエンコードする
				.passwordEncoder(passwordEncoder());
		
		// ③特別のUserServiceを実装
		
	}

//    @Bean
//	public static NoOpPasswordEncoder passwordEncoder() {
//	    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
