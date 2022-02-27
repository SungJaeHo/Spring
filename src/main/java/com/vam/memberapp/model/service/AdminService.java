package com.vam.memberapp.model.service;

import java.util.List;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dto.BookVO;
import com.vam.memberapp.model.dto.CateVO;

public interface AdminService {
	/* 상품 등록 */
	public void bookEnroll(BookVO book) throws Exception;	
	
	/* 카테고리 리스트  */
	public List<CateVO> cateList(CateVO cateVO) throws Exception;;
	
	/* 상품 리스트 */
	public List<BookVO> goodsGetList(Criteria cri) throws Exception; 
	
	/* 상품 총 개수 */
	public int goodsGetTotal(Criteria cri) throws Exception;
	
	/* 상품 조회 페이지 */
	public BookVO goodsGetDetail(int bookId);
	
}
