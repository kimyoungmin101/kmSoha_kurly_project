package com.dailykurly.myapp.info.dto;

public class InfoDto {
	private int Product_id;
	private String product_name;
	private int product_price;
	private String product_imgeurl;
	
	public InfoDto() {}
	
	public InfoDto(int product_id, String product_name, int product_price, String product_imgeurl) {
		super();
		Product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_imgeurl = product_imgeurl;
	}

	public int getProduct_id() {
		return Product_id;
	}

	public void setProduct_id(int product_id) {
		Product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public String getProduct_imgeurl() {
		return product_imgeurl;
	}

	public void setProduct_imgeurl(String product_imgeurl) {
		this.product_imgeurl = product_imgeurl;
	}
	
	
	
}
