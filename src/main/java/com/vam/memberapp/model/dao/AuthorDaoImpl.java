package com.vam.memberapp.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dto.AuthorVO;
import com.vam.memberapp.model.service.AuthorServiceImpl;

@Repository
public class AuthorDaoImpl implements AuthorDao{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorDaoImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void authorEnroll(AuthorVO author) {
		sqlSession.insert("AUTHOR.authorEnroll",author);
	}

	@Override
	public List<AuthorVO> authorGetList(Criteria cri) {
		return sqlSession.selectList("AUTHOR.authorGetList", cri);
	}
	
	@Override
	public int authorGetTotal(Criteria cri) {
		return sqlSession.selectOne("AUTHOR.authorGetTotal");
	}

	@Override
	public AuthorVO authorGetDetail(int authorId) {
		return sqlSession.selectOne("AUTHOR.authorGetDetail", authorId);
	}

	@Override
	public int authorModify(AuthorVO author) {
		logger.info("(daoImpl)authorModify()::::::::>>>>>" + author);
		return sqlSession.update("AUTHOR.authorModify",author);
	}
	
	@Override
	public int authorDelete(int authorId) {
		logger.info("(daoImpl)authorDelete()::::::::>>>>>" + authorId);
		return sqlSession.delete("AUTHOR.authorDelete", authorId);
	}
}
