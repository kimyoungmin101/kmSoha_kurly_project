package com.dailykurly.myapp.recommend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailykurly.myapp.recommend.dao.RecommendDao;
import com.dailykurly.myapp.recommend.dto.RecommendDto;

@Service
public class RecommendServiceImpl implements RecommendService{
	
	@Autowired
	private RecommendDao recommendDao;

	@Override
	public List<RecommendDto> getList(String user_id) {
		
		return recommendDao.getList(user_id);
	}
	
	
}
