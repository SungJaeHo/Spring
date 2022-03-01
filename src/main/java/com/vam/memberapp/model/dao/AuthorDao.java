package com.vam.memberapp.model.dao;

import java.util.List;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dto.AuthorVO;

public interface AuthorDao {
	/* �۰� ��� */
    public void authorEnroll(AuthorVO author);
    
    /* �۰� ��� */
    public List<AuthorVO> authorGetList(Criteria cri);
    
    /* �۰� �� �� */
    public int authorGetTotal(Criteria cri);
   
    /* �۰� �� */
    public AuthorVO authorGetDetail(int authorId);
   
    /* �۰� ���� ����*/
    public int authorModify(AuthorVO author);

	public int authorDelete(int authorId);
    
}
