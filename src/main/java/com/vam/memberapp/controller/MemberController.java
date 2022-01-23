package com.vam.memberapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	//ȸ������ ������ �̵�
	@RequestMapping(value="/member/join", method=RequestMethod.GET)
	public void loginGET() {
		logger.info("ȸ������ ������ ����");		
	}
	//ȸ������
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinPOST(MemberVO member) throws Exception{
		
		logger.info("join ����");
		
		// ȸ������ ���� ����
		memberservice.memberJoin(member);
		
		logger.info("join Service ����");
		
		return "redirect:/main";
		
	}
	
	//�α��� ������ �̵�
	@RequestMapping(value="login",method=RequestMethod.GET)
	public void joinGET() {
		logger.info("�α��� ������ ����");
	}
}