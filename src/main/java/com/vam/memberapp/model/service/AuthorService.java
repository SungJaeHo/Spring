package com.vam.memberapp.model.service;

import java.util.List;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dto.AuthorVO;

public interface AuthorService {
	
	/* �۰� ��� */
    public void authorEnroll(AuthorVO author) throws Exception;
    
    /* �۰� ��� */
    public List<AuthorVO> authorGetList(Criteria cri) throws Exception;
    
    /* �۰� �� �� */
    public int authorGetTotal(Criteria cri) throws Exception;   
}
