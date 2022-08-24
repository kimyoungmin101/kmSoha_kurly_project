package com.dailykurly.myapp.cart.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dailykurly.myapp.cart.dto.CartDto;

@Repository
public class CartDaoImpl implements CartDao{
	
	@Autowired
	private SqlSession session;

	@Override
	public List<CartDto> getList(String user_id) {
		
		return session.selectList("cart.getList", user_id);
	}

	@Override
	public void insert(CartDto dto) {
		
		session.insert("cart.insert", dto);
		
	}

	@Override
	public void delete(int num) {
		
		session.delete("cart.delete", num);
		
	}

	@Override
	public int countCart(String user_id, int product_id) {

		return 0;
	}

	@Override
	public CartDto getData(int product_id) {

		return null;
	}

	@Override
	public int sumMoney(String user_id) {
		
		return session.selectOne("cart.sumMoney", user_id);
	}

	@Override
	public List<CartDto> cartMoney() {

		return null;
	}
	
}
