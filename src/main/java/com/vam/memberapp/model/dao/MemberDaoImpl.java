package com.vam.memberapp.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vam.memberapp.model.dto.MemberVO;


@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SqlSessionTemplate sqlSession;	
	//ȸ�� ���� ���� �׽�Ʈ �޼���

	@Override
	public void memberJoin(MemberVO memberVO) {
		// TODO Auto-generated method stub
		sqlSession.insert("MEMBER.memberJoin",memberVO);
	}
	
}
