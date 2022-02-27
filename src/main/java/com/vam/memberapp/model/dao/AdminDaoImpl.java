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
public class AdminDaoImpl implements AdminDao{
	private static final Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Override
	public void bookEnroll(BookVO book) {
		logger.info("AdminDaoImpl:::>>>>>>>>");
		sqlSession.insert("Admin.bookEnroll",book);
	}
	
	@Override
	public List<CateVO> catelist(CateVO cateVO) {
		logger.info("리스트 DaoImpl 접근 :::>>>>>>>>");
		return sqlSession.selectList("Admin.cateList", cateVO);
	}
	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		logger.info("리스트 DaoImpl goodsGetList 접근 :::>>>>>>>>");
		return sqlSession.selectList("Admin.goodsGetList",cri);
	}
	@Override
	public int goodsGetTotal(Criteria cri) {
		logger.info("리스트 DaoImpl goodsGetTotal 접근 :::>>>>>>>>");
		return sqlSession.selectOne("Admin.goodsGetTotal");
	}
	
	@Override
	public BookVO goodsGetDetail(int bookId) {
		logger.info("상세정보  goodsGetDetail AdminDaoImpl 접근 :::>>>>>>>>");
		return sqlSession.selectOne("Admin.goodsGetDetail", bookId);
	}
}
