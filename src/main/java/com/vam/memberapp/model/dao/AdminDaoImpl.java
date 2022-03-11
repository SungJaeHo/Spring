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
		logger.info("AdminDaoImpl ����Ʈ DaoImpl ���� :::>>>>>>>>");
		return sqlSession.selectList("Admin.cateList", cateVO);
	}
	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		logger.info("AdminDaoImpl ����¡ DaoImpl goodsGetList ���� :::>>>>>>>>");
		return sqlSession.selectList("Admin.goodsGetList",cri);
	}
	@Override
	public int goodsGetTotal(Criteria cri) {
		logger.info("AdminDaoImpl ����¡ �� ����  goodsGetTotal ���� :::>>>>>>>>");
		return sqlSession.selectOne("Admin.goodsGetTotal");
	}
	
	@Override
	public BookVO goodsGetDetail(int bookId) {
		logger.info("AdminDaoImpl��ǰ ������  goodsGetDetail  ���� :::>>>>>>>>");
		return sqlSession.selectOne("Admin.goodsGetDetail", bookId);
	}
	
	@Override
	public int goodsModify(BookVO vo) {
		logger.info("AdminDaoImpl ��ǰ ����  goodsModify  ���� :::>>>>>>>>");
		return sqlSession.update("Admin.goodsModify",vo);
	}
	
	@Override
	public int goodsDelete(int bookId) {
		logger.info("AdminDaoImpl ��ǰ ����  goodsDelete  ���� :::>>>>>>>>");
		return sqlSession.delete("Admin.goodsDelete", bookId);
	}
	
	@Override
	public void imageEnroll(AttachImageVO vo) {
		logger.info("AdminDaoImpl �̹��� ��� :::>>>>>>>>imageEnroll");
		sqlSession.insert("Admin.imageEnroll",vo);
	}
	
	@Override
	public void deleteImageAll(int bookId) {
		logger.info("AdminDaoImpl ���� ��ǰ �̹��� ��ü ���� :::>>>>>>>>deleteImageAll");
		sqlSession.delete("Admin.deleteImageAll", bookId);
	}
	
	@Override
	public List<AttachImageVO> checkFileList() {
		logger.info("AdminDaoImpl ��ġ���α׷� �̹��� :::>>>>>>>>AttachImageVO");
		return sqlSession.selectList("Admin.checkFileList");
	}
}
