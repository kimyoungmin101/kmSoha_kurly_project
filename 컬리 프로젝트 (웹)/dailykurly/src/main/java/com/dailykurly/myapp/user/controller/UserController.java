package com.dailykurly.myapp.user.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dailykurly.myapp.user.dto.UserDto;
import com.dailykurly.myapp.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/user/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("user_id");
		return "user/logout";
	}
	
	//로그인 폼 요청 처리
	@RequestMapping("/user/loginform")
	public String loginform() {
		
		return "user/loginform";
	}
	
	//로그인 요청 처리
	@RequestMapping("/user/login")
	public ModelAndView login(ModelAndView mView, UserDto dto,
			@RequestParam String url, HttpSession session) {
		service.loginProcess(dto, session);
		
		String encodedUrl=URLEncoder.encode(url);
		mView.addObject("url", url);
		mView.addObject("encodedUrl", encodedUrl);
		
		mView.setViewName("user/login");
		return mView;
	}
}
