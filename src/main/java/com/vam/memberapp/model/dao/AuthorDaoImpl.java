package com.vam.memberapp.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vam.memberapp.model.dto.AuthorVO;

@Repository
public class AuthorDaoImpl implements AuthorDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void authorEnroll(AuthorVO author) {
		sqlSession.insert("AUTHOR.authorEnroll",author);
	}	
	
	
}
