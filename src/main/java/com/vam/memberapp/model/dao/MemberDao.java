package com.vam.memberapp.model.dao;

import com.vam.memberapp.model.dto.MemberVO;

public interface MemberDao {

	//회원가입
	public void memberJoin(MemberVO member);
}
