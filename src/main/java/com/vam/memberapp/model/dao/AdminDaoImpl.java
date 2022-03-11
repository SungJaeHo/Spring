package com.vam.memberapp.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dto.AttachImageVO;
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
		System.out.println("Before BookVO :" + book);
		sqlSession.insert("Admin.bookEnroll",book);
		System.out.println("After BookVO :" + book);
	}
	
	@Override
	public List<CateVO> catelist(CateVO cateVO) {
		logger.info("AdminDaoImpl 리스트 DaoImpl 접근 :::>>>>>>>>");
		return sqlSession.selectList("Admin.cateList", cateVO);
	}
	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		logger.info("AdminDaoImpl 페이징 DaoImpl goodsGetList 접근 :::>>>>>>>>");
		return sqlSession.selectList("Admin.goodsGetList",cri);
	}
	@Override
	public int goodsGetTotal(Criteria cri) {
		logger.info("AdminDaoImpl 페이징 총 갯수  goodsGetTotal 접근 :::>>>>>>>>");
		return sqlSession.selectOne("Admin.goodsGetTotal");
	}
	
	@Override
	public BookVO goodsGetDetail(int bookId) {
		logger.info("AdminDaoImpl상품 상세정보  goodsGetDetail  접근 :::>>>>>>>>");
		return sqlSession.selectOne("Admin.goodsGetDetail", bookId);
	}
	
	@Override
	public int goodsModify(BookVO vo) {
		logger.info("AdminDaoImpl 상품 수정  goodsModify  접근 :::>>>>>>>>");
		return sqlSession.update("Admin.goodsModify",vo);
	}
	
	@Override
	public int goodsDelete(int bookId) {
		logger.info("AdminDaoImpl 상품 삭제  goodsDelete  접근 :::>>>>>>>>");
		return sqlSession.delete("Admin.goodsDelete", bookId);
	}
	
	@Override
	public void imageEnroll(AttachImageVO vo) {
		logger.info("AdminDaoImpl 이미지 등록 :::>>>>>>>>imageEnroll");
		sqlSession.insert("Admin.imageEnroll",vo);
	}
	
	@Override
	public void deleteImageAll(int bookId) {
		logger.info("AdminDaoImpl 지정 상품 이미지 전체 삭제 :::>>>>>>>>deleteImageAll");
		sqlSession.delete("Admin.deleteImageAll", bookId);
	}
	
	@Override
	public List<AttachImageVO> checkFileList() {
		logger.info("AdminDaoImpl 배치프로그램 이미지 :::>>>>>>>>AttachImageVO");
		return sqlSession.selectList("Admin.checkFileList");
	}
}
