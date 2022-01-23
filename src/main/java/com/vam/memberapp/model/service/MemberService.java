package com.vam.memberapp.model.service;

import com.vam.memberapp.model.dto.MemberVO;

public interface MemberService {
		//회원가입
		public void memberJoin(MemberVO member) throws Exception;
}
