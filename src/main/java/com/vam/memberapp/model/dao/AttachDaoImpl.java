package com.vam.memberapp.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vam.memberapp.model.dto.AttachImageVO;

@Repository
public class AttachDaoImpl implements AttachDao {
	
private static final Logger logger = LoggerFactory.getLogger(AttachDaoImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<AttachImageVO> getAttachList(int bookId) {
		logger.info("AttachDaoImpl 상품 상세정보  getAttachList  접근 :::>>>>>>>>");
		return sqlSession.selectList("Attach.getAttachList", bookId);
	}
}
