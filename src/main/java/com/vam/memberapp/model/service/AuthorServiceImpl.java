package com.vam.memberapp.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dao.AuthorDao;
import com.vam.memberapp.model.dto.AuthorVO;

@Service
public class AuthorServiceImpl implements AuthorService {

	private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

	@Autowired
	AuthorDao authorDao;

	@Override
	public void authorEnroll(AuthorVO author) throws Exception {
		authorDao.authorEnroll(author);
	}

	@Override
	public List<AuthorVO> authorGetList(Criteria cri) throws Exception {
		logger.info("(service)authorGetList()::::::::>>>>>" + cri);
		return authorDao.authorGetList(cri);
	}

    /* ÀÛ°¡ ÃÑ ¼ö */
    @Override
    public int authorGetTotal(Criteria cri) throws Exception {
        logger.info("(service)authorGetTotal()......." + cri);
        return authorDao.authorGetTotal(cri);
    }

	@Override
	public AuthorVO authorGetDetail(int authorId) throws Exception {
		logger.info("(service)authorGetDetail()......." + authorId);
		return authorDao.authorGetDetail(authorId);
	}

	@Override
	public int authorModify(AuthorVO author) throws Exception {
		logger.info("(service)authorModify()......." + author);
		return authorDao.authorModify(author);
	}
    
    
    
}