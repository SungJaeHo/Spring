package com.vam.memberapp.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dao.AdminDao;
import com.vam.memberapp.model.dto.BookVO;
import com.vam.memberapp.model.dto.CateVO;
@Service
public class AdminServiceImpl implements AdminService{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminDao adminDao ;
	
	@Override
	public void bookEnroll(BookVO book) {
		logger.info("AdminService::>>>>BookEnroll");
		adminDao.bookEnroll(book);
	}
	
	@Override
	public List<CateVO> cateList(CateVO cateVO) {
		logger.info("AdminService::>>>>catelist");
		return adminDao.catelist(cateVO);
	}
	
	/* ��ǰ ����Ʈ */
	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		logger.info("Admin���� goodsGetTotalList:::::>>>>>"+ cri);
		return adminDao.goodsGetList(cri);
	}

	/* ��ǰ �� ���� */
	public int goodsGetTotal(Criteria cri) {
		logger.info("Admin���� goodsGetTotal()::::>>>>>>>>"+ cri);
		return adminDao.goodsGetTotal(cri);
	}	
	
	@Override
	public BookVO goodsGetDetail(int bookId) {
		return adminDao.goodsGetDetail(bookId);
	}
}
