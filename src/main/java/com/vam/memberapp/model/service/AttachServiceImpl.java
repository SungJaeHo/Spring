package com.vam.memberapp.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.memberapp.model.dao.AttachDao;
import com.vam.memberapp.model.dao.AttachDaoImpl;
import com.vam.memberapp.model.dto.AttachImageVO;

@Service
public class AttachServiceImpl implements AttachService{

	private static final Logger logger = LoggerFactory.getLogger(AttachServiceImpl.class);
	
	@Autowired
	private AttachDao attachDao; 
	
	@Override
	public List<AttachImageVO> getAttachList(int bookId) {
		logger.info("(service)getAttachList()......." + bookId);
		return attachDao.getAttachList(bookId);
	}
}
