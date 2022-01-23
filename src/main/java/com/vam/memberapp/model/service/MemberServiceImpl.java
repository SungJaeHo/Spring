package com.vam.memberapp.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.memberapp.model.dao.MemberDao;
import com.vam.memberapp.model.dto.MemberVO;


@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDao memberDao;

	@Override
	public void memberJoin(MemberVO member) throws Exception {
		
		memberDao.memberJoin(member);
		
	}
}
