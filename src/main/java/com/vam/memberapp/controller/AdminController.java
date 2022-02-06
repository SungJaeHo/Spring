package com.vam.memberapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.memberapp.model.dto.AuthorVO;
import com.vam.memberapp.model.service.AuthorService;

@Controller
@RequestMapping("/admin")
public class AdminController {
 
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    
	@Autowired
    private AuthorService authorService;
	
    /* ������ ���� ������ �̵� */
    @RequestMapping(value="/main", method = RequestMethod.GET)
    public void adminMainGET() throws Exception{
        
        logger.info("������ ������ �̵�");
        
    }
    
    /* ��ǰ ��� ������ ���� */
    @RequestMapping(value = "/goodsManage", method = RequestMethod.GET)
    public void goodsManageGET() throws Exception{
        logger.info("��ǰ ��� ������ ����");
    }
    
    /* ��ǰ ��� ������ ���� */
    @RequestMapping(value = "/goodsEnroll", method = RequestMethod.GET)
    public void goodsEnrollGET() throws Exception{
        logger.info("��ǰ ��� ������ ����");
    }
    
    /* �۰� ��� ������ ���� */
    @RequestMapping(value = "/authorEnroll", method = RequestMethod.GET)
    public void authorEnrollGET() throws Exception{
        logger.info("�۰� ��� ������ ����");
    }
    
    /* �۰� ���� ������ ���� */
    @RequestMapping(value = "/authorManage", method = RequestMethod.GET)
    public void authorManageGET() throws Exception{
        logger.info("�۰� ���� ������ ����");
    }    
    
    @RequestMapping(value="/authorEnroll", method = RequestMethod.POST)
    public String authorEnrollPOST(AuthorVO author,HttpServletRequest request, RedirectAttributes rttr) throws Exception{
    	 
    	 
        logger.info("authorEnroll :" +  author);
        
        authorService.authorEnroll(author);      // �۰� ��� ���� ����
        
        rttr.addFlashAttribute("enroll_result", author.getAuthorName());
        logger.info("rttr :" +  rttr);
        
        return "redirect:/admin/authorManage";
    }
}