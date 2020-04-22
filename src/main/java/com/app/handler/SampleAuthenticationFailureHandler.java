package com.app.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class SampleAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory.getLogger(SampleAuthenticationFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		logger.error("ERROR!ERROR!ERROR!ERROR!ERROR!ERROR!ERROR!");

		String errorId = "";
		// ExceptionからエラーIDをセットする
		if (exception instanceof BadCredentialsException) {
			errorId = "ERROR";
		}

		// ログイン画面にリダイレクトする
		response.sendRedirect(
				request.getContextPath() + "/" + request.getParameter("identifier") + "/index?error=" + errorId);
	}

}
