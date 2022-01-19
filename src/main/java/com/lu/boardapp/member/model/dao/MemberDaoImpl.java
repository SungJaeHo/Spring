package com.lu.boardapp.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lu.boardapp.member.model.dto.Member;

@Repository
// @Repository 어노테이션
// 해당 클래스를 Dao 역할을 하는 Bean 으로 등록
// SqlException을 DataAccessException 으로 전환해준다.
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertMember(Member member) {
		
		System.out.println("dao >>>>> member: " + member);
		
		return sqlSession.insert("MEMBER.insertMember",	member);
	}

	//select 쿼리 : 1.결과가 하나일 경우 
	//				selectOne(네임스페이스 및 아이디 지정, 매핑시킬 객체); 
	//			  2.결과가 여러개인 경우
	//				selectList(네임스페이스 및 아이디 지정, 매핑시킬 객체);
	//			resultType으로 지정한 객체의 list를 반환
	//			MEMBER를 resultType으로 지정한 경우 List<MEMBER>를 반환
	//insert 쿼리  sqlSessionTemplate.insert(네임스페이스 및 아이디 지정, 매핑시킬 객체)
	//update 쿼리  sqlSessionTemplate.update(네임스페이스 및 아이디 지정, 매핑시킬 객체)
	//delete 쿼리  sqlSessionTemplate.delete(네임스페이스 및 아이디 지정, 매핑시킬 객체)

	//매핑 시킬 객체가 필요하지 않다면 두번째 매개변수는 생략
	public Member selectMember(
			Map<String, Object> commandMap) {
		return sqlSession.selectOne("MEMBER.selectMember",commandMap);
	}

}
