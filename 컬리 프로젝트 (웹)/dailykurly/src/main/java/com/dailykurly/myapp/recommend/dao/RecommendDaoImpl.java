package com.dailykurly.myapp.recommend.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dailykurly.myapp.recommend.dto.RecommendDto;

@Repository
public class RecommendDaoImpl implements RecommendDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public List<RecommendDto> getList(String user_id) {
		
		return session.selectList("recommend.getList", user_id);
	}

}
