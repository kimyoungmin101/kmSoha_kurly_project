package com.dailykurly.myapp.cart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dailykurly.myapp.cart.dto.CartDto;

public interface CartService {
	
	public List<CartDto> getList(String user_id);
	public List<CartDto> cartMoney();
	public void insert(CartDto dto);
	public void delete(int cart_number, HttpServletRequest request);
	public int sumMoney(String user_id);
	public int countCart(String user_id, int product_id);
}
