package com.vam.memberapp.model.dao;

import com.vam.memberapp.model.dto.MemberVO;

public interface MemberDao {

	//ȸ������
	public void memberJoin(MemberVO member);
	
	// ���̵� �ߺ� �˻�
	public int idCheck(String memberId);
	
	public MemberVO memberLogin(MemberVO member);
}
