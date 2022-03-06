package com.vam.memberapp.model.dao;

import java.util.List;

import com.vam.memberapp.model.dto.AttachImageVO;

public interface AttachDao {
	
	/* 이미지 데이터 반환 */
	public List<AttachImageVO> getAttachList(int bookId);
	
}
