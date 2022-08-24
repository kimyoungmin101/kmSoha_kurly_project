package com.dailykurly.myapp.info.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.dailykurly.myapp.info.dao.InfoDao;
import com.dailykurly.myapp.info.dto.InfoDto;


@Service
public class InfoServiceImpl implements InfoService{
	
	@Autowired
	InfoDao dao;

	@Override
	public void getList(HttpServletRequest request) {
		
		InfoDto dto = new InfoDto();
		List<InfoDto> list = dao.getList(dto);
		
		request.setAttribute("list", list);
		
	}

	@Override
	public void getInfo(ModelAndView mView, int product_id) {
		
		InfoDto dto = dao.getInfo(product_id);
		
		mView.addObject("dto", dto);
		
	}


}
