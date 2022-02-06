package com.vam.memberapp.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.memberapp.model.dao.AuthorDao;
import com.vam.memberapp.model.dto.AuthorVO;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired AuthorDao authorDao;
	
	@Override
	public void authorEnroll(AuthorVO author) throws Exception {
		authorDao.authorEnroll(author);
	}

}