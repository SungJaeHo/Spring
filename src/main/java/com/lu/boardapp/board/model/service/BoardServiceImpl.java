package com.lu.boardapp.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lu.boardapp.board.model.dao.BoardDao;
import com.lu.boardapp.board.model.dto.BoardVo;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;

	// 게시글 작성
	@Override
	public void write(BoardVo boardVo) {
		boardDao.write(boardVo);
	}

}
