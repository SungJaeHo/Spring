package com.vam.memberapp.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.memberapp.model.dao.AdminDao;
import com.vam.memberapp.model.dto.BookVO;
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
}
