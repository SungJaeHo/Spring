package com.vam.memberapp.model.service;

import com.vam.memberapp.model.dto.MemberVO;

public interface MemberService {
		//ȸ������
		public void memberJoin(MemberVO member) throws Exception;
		
		// ���̵� �ߺ� �˻�
		public int idCheck(String memberId) throws Exception;
		
		public MemberVO memberLogin(MemberVO member) throws Exception;
}
