package com.dailykurly.myapp.recommend.dto;

public class RecommendDto {
	private String user_id;
	private int product_id;
	private String product_imgeurl;
	private String product_name;
	
	public RecommendDto() {}

	public RecommendDto(String user_id, int product_id, String product_imgeurl, String product_name) {
		super();
		this.user_id = user_id;
		this.product_id = product_id;
		this.product_imgeurl = product_imgeurl;
		this.product_name = product_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public String getProduct_imgeurl() {
		return product_imgeurl;
	}

	public void setProduct_imgeurl(String product_imgeurl) {
		this.product_imgeurl = product_imgeurl;
	}
	
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
}
