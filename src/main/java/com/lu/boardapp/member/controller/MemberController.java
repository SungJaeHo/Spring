package com.lu.boardapp.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lu.boardapp.member.model.dto.Member;
import com.lu.boardapp.member.model.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	// 로그인 폼 view 페이지
	@RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
	public String logIn() {
		System.out.println("login 메서드 호출");
		return "/member/login"; // views 폴더에 member 폴더에 login.jsp 찾아감
	}
	
	// 로그인 동작
	@RequestMapping(value = "/member/loginimpl.do", method = RequestMethod.POST)
	public String logInImpl(
			@RequestParam Map<String, Object> commandMap
			,HttpSession session
			, Model model
			) {
		
		Member res = memberService.selectMember(commandMap);
		
		// 로그인에 성공한다면
		if(res != null) {
			session.setAttribute("logInInfo", res);
			model.addAttribute("alertMsg", "로그인 성공~");
			model.addAttribute("url", "mypage.do");
		} else {
			model.addAttribute("alertMsg", "로그인 실패");
			model.addAttribute("url", "login.do");
		}
		
		return "common/result";
	}
	
	// 회원가입 폼 view 페이지
	@RequestMapping(value = "/member/join.do", method = RequestMethod.GET)
	public void join() {}
	
	@RequestMapping(value = "/member/joinimpl.do", method = RequestMethod.POST)
	public String joinImpl(
			//@ModelAttribute
			// 1. html 태그의 name 속성명과 Command 객체(Dto, Map<String, Object>) 의
			// 필드 변수명이 같은 경우
			
			@ModelAttribute Member member
			, Model model
			, HttpServletRequest request
			) {
		
		int result = memberService.insertMember(member);
		
		if(result > 0) {
			// addAttribute: ModelAndView 의 addObject 와 같다
			model.addAttribute("alertMsg", "회원가입에 성공했습니다.");
			model.addAttribute("url", request.getContextPath() + "/member/login.do");
		} else {
			model.addAttribute("alertMsg", "회원가입에 실패했습니다.");
			model.addAttribute("url", request.getContextPath() + "/member/join.do");
			
		}
		
		return "common/result";
	}
	
	// 마이페이지 View
	@RequestMapping("/member/mypage.do")
	public void mypage() {}
	
}
