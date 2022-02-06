package com.vam.memberapp.model.dao;

import com.vam.memberapp.model.dto.AuthorVO;

public interface AuthorDao {
	/* 작가 등록 */
    public void authorEnroll(AuthorVO author);
}
