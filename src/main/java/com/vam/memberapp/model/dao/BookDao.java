package com.vam.memberapp.model.dao;

import java.util.List;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dto.BookVO;
import com.vam.memberapp.model.dto.CateVO;

public interface BookDao {
	
	/* ��ǰ �˻� */
	public List<BookVO> getGoodsList(Criteria cri);
	
	/* ��ǰ �� ���� */
	public int goodsGetTotal(Criteria cri);
	
	/* �۰� id ����Ʈ ��û */
	public String[] getAuthorIdList(String keyword);
	
	/* ���� ī�װ� ����Ʈ */
	public List<CateVO> getCateCode1();
	/* ���� ī�װ� ����Ʈ */
	public List<CateVO> getCateCode2();
	
}
