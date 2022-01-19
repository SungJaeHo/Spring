package com.lu.boardapp.member.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lu.boardapp.member.model.dao.MemberDao;
import com.lu.boardapp.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public int insertMember(Member member) {
		return memberDao.insertMember(member);
	}
	
	@Override
	public Member selectMember(Map<String, Object> commandMap) {
		return memberDao.selectMember(commandMap);
	}

}
