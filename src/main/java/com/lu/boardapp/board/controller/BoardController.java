package com.lu.boardapp.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lu.boardapp.board.model.dto.BoardVo;
import com.lu.boardapp.board.model.service.BoardService;
import com.lu.boardapp.member.model.service.MemberService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	// 게시판 글 작성 화면
	@RequestMapping(value = "/board/writeView", method = RequestMethod.GET)
	public void writeView() {
		System.out.println("접속완료");
	}

	// 게시판 글 작성
	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String write(BoardVo boardVo) {
		boardService.write(boardVo);
		System.out.println("글작성");
		return "/board/writeView";
	}

}
