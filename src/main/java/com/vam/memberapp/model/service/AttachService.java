package com.vam.memberapp.model.service;

import java.util.List;

import com.vam.memberapp.model.dto.AttachImageVO;

public interface AttachService {
	/* 이미지 데이터 반환 */
	public List<AttachImageVO> getAttachList(int bookId);
	
}
