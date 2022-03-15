package com.vam.memberapp.model.service;

import java.util.List;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dto.AttachImageVO;
import com.vam.memberapp.model.dto.BookVO;
import com.vam.memberapp.model.dto.CateVO;

public interface AdminService {
	/* ��ǰ ��� */
	public void bookEnroll(BookVO book) throws Exception;	
	
	/* ī�װ� ����Ʈ  */
	public List<CateVO> cateList(CateVO cateVO) throws Exception;;
	
	/* ��ǰ ����Ʈ */
	public List<BookVO> goodsGetList(Criteria cri) throws Exception; 
	
	/* ��ǰ �� ���� */
	public int goodsGetTotal(Criteria cri) throws Exception;
	
	/* ��ǰ ��ȸ ������ */
	public BookVO goodsGetDetail(int bookId);
	
	/* ��ǰ ���� */
	public int goodsModify(BookVO vo);
	
	/* ��ǰ ���� */
	public int goodsDelete(int bookId);
	
	/* ���� ��ǰ �̹��� ���� ��� */
	public List<AttachImageVO> getAttachInfo(int bookId);
	
}
