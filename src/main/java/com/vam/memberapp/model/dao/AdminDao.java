package com.vam.memberapp.model.dao;

import com.vam.memberapp.model.dto.BookVO;

public interface AdminDao {
	/* 상품 등록 */
	public void bookEnroll(BookVO book);
}
