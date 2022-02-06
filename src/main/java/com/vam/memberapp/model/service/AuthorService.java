package com.vam.memberapp.model.service;

import com.vam.memberapp.model.dto.AuthorVO;

public interface AuthorService {
	/* 작가 등록 */
    public void authorEnroll(AuthorVO author) throws Exception;
}
