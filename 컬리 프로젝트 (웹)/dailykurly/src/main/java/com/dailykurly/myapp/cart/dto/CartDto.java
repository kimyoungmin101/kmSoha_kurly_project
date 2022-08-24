package com.dailykurly.myapp.cart.dto;

public class CartDto {
	
	private int cart_number;
	private String user_id;
	private String product_name;
	private int product_id;
	private int product_price;
	private int product_count;
	private String product_imgeurl;
	private int money;
	
	public CartDto() {}

	public CartDto(int cart_number, String user_id, int money, String product_imgeurl, String product_name, int product_id, int product_price, int product_count) {
		super();
		this.cart_number = cart_number;
		this.user_id = user_id;
		this.product_name = product_name;
		this.product_id = product_id;
		this.product_price = product_price;
		this.product_count = product_count;
		this.product_imgeurl=product_imgeurl;
		this.money=money;
	}

	public int getCart_number() {
		return cart_number;
	}

	public void setCart_number(int cart_number) {
		this.cart_number = cart_number;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public int getProduct_count() {
		return product_count;
	}

	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}
	
	public String getProduct_imgeurl() {
		return product_imgeurl;
	}

	public void setProduct_imgeurl(String product_imgeurl) {
		this.product_imgeurl = product_imgeurl;
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
