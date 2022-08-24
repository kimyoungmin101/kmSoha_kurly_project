package com.dailykurly.myapp.cart.dao;

import java.util.List;

import com.dailykurly.myapp.cart.dto.CartDto;

public interface CartDao {
	
	//장바구니 상품목록 조회한 결과
	public List<CartDto> getList(String user_id);
	//장바구니 상품 insert
	public void insert(CartDto dto);
	//장바구니 상품 개별 delete
	public void delete(int num);
	//장바구니 상품 갯수
	public int countCart(String user_id, int product_id);
	//글 정보 얻어오기
	public CartDto getData(int product_id);
	//장바구니 총 합계
	public int sumMoney(String user_id);
	//상품별 금액
	public List<CartDto> cartMoney();
}
