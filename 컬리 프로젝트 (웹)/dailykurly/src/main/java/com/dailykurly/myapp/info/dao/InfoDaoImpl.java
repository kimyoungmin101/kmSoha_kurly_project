package com.dailykurly.myapp.info.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dailykurly.myapp.info.dto.InfoDto;



@Repository
public class InfoDaoImpl implements InfoDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public List<InfoDto> getList(InfoDto dto) {
		
		return session.selectList("info.getList", dto);
	}

	@Override
	public InfoDto getInfo(int num) {
		
		return session.selectOne("info.getInfo", num);
	}
	
	
}
