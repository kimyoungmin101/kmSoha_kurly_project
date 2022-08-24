package com.dailykurly.myapp.info.dao;

import java.util.List;

import com.dailykurly.myapp.info.dto.InfoDto;



public interface InfoDao {
	
	public List<InfoDto> getList(InfoDto dto);
	
	public InfoDto getInfo(int product_id);
}
