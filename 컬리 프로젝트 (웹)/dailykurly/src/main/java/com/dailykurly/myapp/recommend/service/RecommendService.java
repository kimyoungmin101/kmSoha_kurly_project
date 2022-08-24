package com.dailykurly.myapp.recommend.service;

import java.util.List;

import com.dailykurly.myapp.recommend.dto.RecommendDto;

public interface RecommendService {

	public List<RecommendDto> getList(String user_id);
}
