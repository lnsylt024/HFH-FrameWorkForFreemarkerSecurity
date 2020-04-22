package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HfhFrameWorkForFreemakerSecurityApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HfhFrameWorkForFreemakerSecurityApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbc;

	@Override
	public void run(String... args) throws Exception {
		String password = "0000";
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		password= bpe.encode(password);
		jdbc.update("insert into tbl_accounts(username, password,address,role,enabled) values('admin','" + password
				+ "','東京','ROLE_ADMIN',true)");
		jdbc.update("insert into tbl_accounts(username, password,address,role,enabled) values('litao','" + password
				+ "','東京','ROLE_USER',true)");
		jdbc.update("insert into tbl_accounts(username, password,address,role,enabled) values('test','" + password
				+ "','テスト','ROLE_TEST',false)");
	}

}
