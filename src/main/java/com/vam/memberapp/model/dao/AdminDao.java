package com.vam.memberapp.model.dao;

import java.util.List;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dto.AttachImageVO;
import com.vam.memberapp.model.dto.BookVO;
import com.vam.memberapp.model.dto.CateVO;

public interface AdminDao {
	/* 상품 등록 */
	public void bookEnroll(BookVO book);
	
	/* 카테고리 리스트 */
	public List<CateVO> catelist(CateVO cateVO);
	
	/* 상품 리스트 */
	public List<BookVO> goodsGetList(Criteria cri);
	
	public int goodsGetTotal(Criteria cri);
	
	public BookVO goodsGetDetail(int bookId);
	
	/* 상품 수정 */
	public int goodsModify(BookVO vo);
	
	/* 상품 삭제 */
	public int goodsDelete(int bookId);
	
	/* 이미지 등록 */
	public void imageEnroll(AttachImageVO vo);
	
	/* 지정 상품 이미지 전체 삭제  */
	public void deleteImageAll(int bookId);
	
	/* 어제자 날짜 이미지 리스트 */
	public List<AttachImageVO> checkFileList();
	
	/* 지정 상품 이미지 정보 얻기 */
	public List<AttachImageVO> getAttachInfo(int bookId);
	
}
