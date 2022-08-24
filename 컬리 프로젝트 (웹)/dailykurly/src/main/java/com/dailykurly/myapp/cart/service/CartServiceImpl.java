package com.dailykurly.myapp.cart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailykurly.myapp.cart.dao.CartDao;
import com.dailykurly.myapp.cart.dto.CartDto;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartDao cartDao;

	@Override
	public List<CartDto> getList(String user_id) {
		
		return cartDao.getList(user_id);
	}

	@Override
	public List<CartDto> cartMoney() {
		
		return null;
	}

	@Override
	public void insert(CartDto dto) {
		
		cartDao.insert(dto);
	}

	@Override
	public void delete(int cart_number, HttpServletRequest request) {
		
		cartDao.delete(cart_number);
	}

	@Override
	public int sumMoney(String user_id) {
		
		return cartDao.sumMoney(user_id);
	}

	@Override
	public int countCart(String user_id, int product_id) {
		
		return 0;
	}
	
	
}
