package com.vam.memberapp.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vam.memberapp.model.dto.MemberVO;


@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SqlSessionTemplate sqlSession;	
	//회원 가입 쿼리 테스트 메서드

	@Override
	public void memberJoin(MemberVO memberVO) {
		// TODO Auto-generated method stub
		sqlSession.insert("MEMBER.memberJoin",memberVO);
	}
	
	@Override
	public int idCheck(String memberId) {
		return sqlSession.selectOne("MEMBER.idCheck", memberId);
	}
	@Override
	public MemberVO memberLogin(MemberVO member) {
		
		MemberVO lvo = null;
		
		try {
			lvo = sqlSession.selectOne("MEMBER.memberLogin",member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MemberDaoImpl : " + lvo);
		return lvo;
	}
}
