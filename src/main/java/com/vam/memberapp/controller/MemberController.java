package com.vam.memberapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vam.memberapp.model.dto.MemberVO;
import com.vam.memberapp.model.service.MemberService;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberservice;

	@RequestMapping(value = "/board/writeView", method = RequestMethod.GET)
	public void writeView() {
		System.out.println("sss");
	}

	// 회원가입 페이지 이동
	@RequestMapping(value = "/member/join", method = RequestMethod.GET)
	public void loginGET() {
		logger.info("회원가입 페이지 진입");
	}

	// 회원가입 버튼 누르면 작동
	@RequestMapping(value = "/member/join", method = RequestMethod.POST)
	public String joinPOST(MemberVO member) throws Exception {

		logger.info("join 진입");

		// 회원가입 서비스 실행
		memberservice.memberJoin(member);

		logger.info("join Service 성공");

		return "redirect:/member/login";

	}

	// 로그인 페이지 이동
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public void joinGET() {
		logger.info("로그인 페이지 진입");
	}

	// 아이디 중복 검사
	@RequestMapping(value = "/member/memberIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String memberIdChkPOST(String memberId, HttpServletResponse response) throws Exception {
		//logger.info("memberIdChk() 진입");
		int result = memberservice.idCheck(memberId);
		//logger.info("결과값 = " + result);
		if(result != 0) {
			return "fail";	// 중복 아이디가 존재
		} else {
			return "success";	// 중복 아이디 x
		}	

	} // memberIdChkPOST() 종료


}
