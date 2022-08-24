package com.dailykurly.myapp.info.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public interface InfoService {
	
	public void getList(HttpServletRequest request);
	
	public void getInfo(ModelAndView mView, int product_id);
}
