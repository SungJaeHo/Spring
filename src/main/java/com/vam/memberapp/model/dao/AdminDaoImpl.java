package com.vam.memberapp.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vam.memberapp.model.dto.BookVO;
@Repository
public class AdminDaoImpl implements AdminDao{
	private static final Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Override
	public void bookEnroll(BookVO book) {
		logger.info("AdminDaoImpl:::>>>>>>>>");
		sqlSession.insert("Admin.bookEnroll",book);
	}
}
