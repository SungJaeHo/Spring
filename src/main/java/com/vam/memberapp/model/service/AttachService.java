package com.vam.memberapp.model.service;

import java.util.List;

import com.vam.memberapp.model.dto.AttachImageVO;

public interface AttachService {
	/* �̹��� ������ ��ȯ */
	public List<AttachImageVO> getAttachList(int bookId);
	
}
