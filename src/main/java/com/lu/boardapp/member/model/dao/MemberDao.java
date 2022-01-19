package com.lu.boardapp.member.model.dao;

import java.util.Map;

import com.lu.boardapp.member.model.dto.Member;

public interface MemberDao {

	/**
	 * 회원가입
	 * 
	 * @param member
	 * @return
	 */
	public int insertMember(Member member);

	/**
	 * 로그인을 위한 회원 조회
	 * 
	 * @param commandMap
	 * @return
	 */
	public Member selectMember(Map<String, Object> commandMap);

}
