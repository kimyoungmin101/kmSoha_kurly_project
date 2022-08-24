package com.dailykurly.myapp.info.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dailykurly.myapp.info.service.InfoService;


@Controller
public class InfoController {
	
	@Autowired
	private InfoService service;
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView getInfo(ModelAndView mView, @RequestParam int product_id) {
		
		service.getInfo(mView, product_id);
		mView.setViewName("info");
		
		return mView;
	}
	
}
