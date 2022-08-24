package com.dailykurly.myapp.user.dao;

import com.dailykurly.myapp.user.dto.UserDto;

public interface UserDao {
	
	//인자로 전달하는 아이디에 해당하는 정보를 리턴하는 메소드
	public UserDto getData(String user_id);
	
	//인자로 전달된 아이디가 존재 하는지 여부를 리턴하는 메소드 
	public boolean isExist(String inputId);
}
