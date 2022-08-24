package com.dailykurly.myapp.user.dto;

public class UserDto {
	
	private String user_id;
	
	public UserDto() {}

	public UserDto(String user_id) {
		super();
		this.user_id = user_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
