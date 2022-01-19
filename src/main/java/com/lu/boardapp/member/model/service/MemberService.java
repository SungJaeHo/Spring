package com.lu.boardapp.member.model.service;

import java.util.Map;

import com.lu.boardapp.member.model.dto.Member;

public interface MemberService {

	/**
	 * 회원가입
	 * 
	 * @param member
	 * @return
	 */
	public int insertMember(Member member);

	/**
	 * 로그인 용 회원 조회
	 * @param commandMap
	 * @return
	 */
	public Member selectMember(Map<String, Object> commandMap);

}
