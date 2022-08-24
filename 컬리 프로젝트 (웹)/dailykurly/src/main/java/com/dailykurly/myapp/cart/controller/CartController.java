package com.dailykurly.myapp.cart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dailykurly.myapp.cart.dto.CartDto;
import com.dailykurly.myapp.cart.service.CartService;
import com.dailykurly.myapp.recommend.dto.RecommendDto;
import com.dailykurly.myapp.recommend.service.RecommendService;


@Controller
public class CartController {
	
	@Autowired
	private CartService service;
	
	@Autowired
	private RecommendService service2;
	
	@RequestMapping("/calendar/insert")
	public ModelAndView calInsert(CartDto dto, HttpSession session, HttpServletRequest request) {
		
		String user_id=(String) session.getAttribute("user_id");
		
		dto.setUser_id(user_id);
		
		service.insert(dto);
		return new ModelAndView("redirect:/calendar/calendar.do");
	}
	
	@RequestMapping("/calendar/calendar.do")
	public ModelAndView calGetList(HttpSession session, ModelAndView mView) {
		
		Map<String, Object> map=new HashMap<String, Object>();
	    String id=(String)session.getAttribute("user_id");
	    List<CartDto> list=service.getList(id);
	    List<RecommendDto> list2=service2.getList(id);
	    int sumMoney=service.sumMoney(id);
	      
	    map.put("sumMoney", sumMoney);
	    map.put("list", list);
	    map.put("list2", list2);
	    map.put("count", list.size());
	      
	    mView.setViewName("calendar/calendar");  
	    mView.addObject("map", map);
	      
	    return mView;
	}
	
	@RequestMapping("/calendar/calendar")
	public ModelAndView calDelete(@RequestParam int product_id, HttpServletRequest request) {
			
		service.delete(product_id, request);
			
		return new ModelAndView("redirect:/calendar/calendar.do");
	}
	
}
