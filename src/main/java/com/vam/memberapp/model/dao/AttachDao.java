package com.vam.memberapp.model.dao;

import java.util.List;

import com.vam.memberapp.model.dto.AttachImageVO;

public interface AttachDao {
	
	/* �̹��� ������ ��ȯ */
	public List<AttachImageVO> getAttachList(int bookId);
	
}
