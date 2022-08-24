package com.dailykurly.myapp.user.service;

import java.util.Map;

import javax.servlet.http.HttpSession;


import com.dailykurly.myapp.user.dto.UserDto;


public interface UserService {
	public Map<String, Object> isExistId(String inputId);
	public void loginProcess(UserDto dto, HttpSession session);
}
