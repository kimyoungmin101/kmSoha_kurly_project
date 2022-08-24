package com.dailykurly.myapp.user.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailykurly.myapp.user.dao.UserDao;
import com.dailykurly.myapp.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao dao;

	@Override
	public Map<String, Object> isExistId(String inputId) {
		//Map 객체를 생성해서 
		Map<String, Object> map=new HashMap<String, Object>();
		//isExist 라는 키값으로 아이디가 존재하는지 여부를 담고 
		map.put("isExist", dao.isExist(inputId));
		//Map 객체를 리턴해 준다. 
		return map;
	}

	@Override
	public void loginProcess(UserDto dto, HttpSession session) {
		
		// 로그인 폼에 입력한 아이디를 이용해서 해당 정보를 select 해 본다. 
		UserDto result=dao.getData(dto.getUser_id());
		if(result != null) {//만일 존재하는 아이디 라면
			// 로그인
			session.setAttribute("user_id", dto.getUser_id());
		}
		
	}
	
	
}
