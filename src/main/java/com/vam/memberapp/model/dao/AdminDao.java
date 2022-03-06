package com.vam.memberapp.model.dao;

import java.util.List;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dto.AttachImageVO;
import com.vam.memberapp.model.dto.BookVO;
import com.vam.memberapp.model.dto.CateVO;

public interface AdminDao {
	/* ��ǰ ��� */
	public void bookEnroll(BookVO book);
	
	/* ī�װ� ����Ʈ */
	public List<CateVO> catelist(CateVO cateVO);
	
	/* ��ǰ ����Ʈ */
	public List<BookVO> goodsGetList(Criteria cri);
	
	public int goodsGetTotal(Criteria cri);
	
	public BookVO goodsGetDetail(int bookId);
	
	/* ��ǰ ���� */
	public int goodsModify(BookVO vo);
	
	/* ��ǰ ���� */
	public int goodsDelete(int bookId);
	
	/* �̹��� ��� */
	public void imageEnroll(AttachImageVO vo);
}
