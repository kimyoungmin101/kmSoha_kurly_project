package com.dailykurly.myapp.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dailykurly.myapp.user.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SqlSession session;

	@Override
	public UserDto getData(String user_id) {
		
		return session.selectOne("user.getData", user_id);
	}

	@Override
	public boolean isExist(String inputId) {
		
	String user_id=session.selectOne("user.isExist", inputId);
			
		if(user_id==null) {
			return false;
		}else {
			return true;
		}
	}
}
