package com.vam.memberapp.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dto.BookVO;
import com.vam.memberapp.model.dto.CateVO;

@Repository
public class BookDaoImpl implements BookDao{
	private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);
	
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<BookVO> getGoodsList(Criteria cri) {
		logger.info("BookDaoImpl ����¡  getGoodsList ���� :::>>>>>>>>" + cri );
		return sqlSession.selectList("BOOK.getGoodsList", cri );
	}
	
	@Override
	public int goodsGetTotal(Criteria cri) {
		logger.info("BookDaoImpl ����¡  goodsGetTotal ���� :::>>>>>>>>" + cri );
		return sqlSession.selectOne("BOOK.goodsGetTotal");
	}
	
	@Override
	public String[] getAuthorIdList(String keyword) {
		logger.info("BookDaoImpl ����¡  getAuthorIdList ���� :::>>>>>>>>" + keyword );
		return sqlSession.selectOne("BOOK.getAuthorIdList", keyword);
	}
	
	@Override
	public List<CateVO> getCateCode1() {
		logger.info("BookDaoImpl ����¡  getCateCode1  ���� :::>>>>>>>>");
		return sqlSession.selectList("getCateCode1");
	}
	
	@Override
	public List<CateVO> getCateCode2() {
		logger.info("BookDaoImpl ����¡  getCateCode2  ���� :::>>>>>>>>");
		return sqlSession.selectList("getCateCode2");
	}
}
