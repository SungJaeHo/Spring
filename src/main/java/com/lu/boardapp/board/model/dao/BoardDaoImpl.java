package com.lu.boardapp.board.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lu.boardapp.board.model.dto.BoardVo;

@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	// 게시글 작성
	@Override
	public void write(BoardVo boardVo) {
		sqlSession.insert("BOARD.insert", boardVo);
	}

}
