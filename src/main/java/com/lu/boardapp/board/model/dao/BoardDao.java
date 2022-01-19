package com.lu.boardapp.board.model.dao;

import com.lu.boardapp.board.model.dto.BoardVo;

public interface BoardDao {
	// 게시글 작성
	public void write(BoardVo boardVo);
}
