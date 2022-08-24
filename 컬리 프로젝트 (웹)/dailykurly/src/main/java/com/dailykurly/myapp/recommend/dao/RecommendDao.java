package com.dailykurly.myapp.recommend.dao;

import java.util.List;

import com.dailykurly.myapp.recommend.dto.RecommendDto;

public interface RecommendDao {
	
	public List<RecommendDto> getList(String user_id);
}
