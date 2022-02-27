package com.vam.memberapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dto.AuthorVO;
import com.vam.memberapp.model.dto.BookVO;
import com.vam.memberapp.model.dto.CateVO;
import com.vam.memberapp.model.dto.PageDTO;
import com.vam.memberapp.model.service.AdminService;
import com.vam.memberapp.model.service.AuthorService;

@Controller
@RequestMapping("/admin")
public class AdminController {
 
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    
	@Autowired
    private AuthorService authorService;
	
	@Autowired AdminService adminService;
	
    /* ������ ���� ������ �̵� */
    @RequestMapping(value="/main", method = RequestMethod.GET)
    public void adminMainGET() throws Exception{
        
        logger.info("������ ������ �̵�");
        
    }
    
    /* ��ǰ ��� ������ ���� */
    @RequestMapping(value = "/goodsManage", method = RequestMethod.GET)
    public void goodsManageGET(Criteria cri, Model model) throws Exception{
        logger.info("��ǰ ����(��ǰ���) ������ ����");
        
		/* ��ǰ ����Ʈ ������ */
		List list = adminService.goodsGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		logger.info("��ǰ ����(��ǰ���) ������ ����"+ list);
		/* ������ �������̽� ������ */
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));
        
    }
    
    /* ��ǰ ��� ������ ���� */
    @RequestMapping(value = "/goodsEnroll", method = RequestMethod.GET)
    public void goodsEnrollGET(Model model , CateVO cateVO) throws Exception{
        
    	logger.info("��ǰ ��� ������ ����");
        
    	ObjectMapper objm = new ObjectMapper();
    	
        List list = adminService.cateList(cateVO);
        
        String cateList = objm.writeValueAsString(list);
        
        model.addAttribute("cateList",cateList);
        
        logger.info("������ ::::::>>>>>>>>>" + list );
        logger.info("������ ::::::>>>>>>>>>" + cateList);
        
    }
    
	/* ��ǰ ��ȸ ������ */
	@GetMapping("/goodsDetail")
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model) {
		
		logger.info("����ȸ ������ Controller goodsDetail()........." + bookId);
		
		/* ��� ������ ���� ���� */
		model.addAttribute("cri", cri);
		
		/* ��ȸ ������ ���� */
		model.addAttribute("goodsInfo", adminService.goodsGetDetail(bookId));
		
	}
    
    /* �۰� ��� ������ ���� */
    @RequestMapping(value = "/authorEnroll", method = RequestMethod.GET)
    public void authorEnrollGET() throws Exception{
        logger.info("�۰� ��� ������ ����");
    }
    
    /* �۰� ���� ������ ���� */
    @RequestMapping(value = "/authorManage", method = RequestMethod.GET)
    public void authorManageGET(Criteria cri , Model model) throws Exception{
        logger.info("�۰� ���� ������ ����:::::::>>>>>" + cri );
        
        /* �۰� ��� ��� ������ */
        List list = authorService.authorGetList(cri);
        
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	// �۰� ���� ���
		} else {
			model.addAttribute("listCheck", "empty");	// �۰� �������� ���� ���
		}
        
        /* ������ �̵� �������̽� ������*/
        int total = authorService.authorGetTotal(cri);
        
        PageDTO pageMaker = new PageDTO(cri, total);
        
        model.addAttribute("pageMaker", pageMaker);
        
        /* ������ �̵� �������̽� ������ */
        model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));
    }    
    
    @RequestMapping(value="/authorEnroll", method = RequestMethod.POST)
    public String authorEnrollPOST(AuthorVO author,HttpServletRequest request, RedirectAttributes rttr) throws Exception{
    	 
    	 
        logger.info("authorEnroll :" +  author);
        
        authorService.authorEnroll(author);      // �۰� ��� ���� ����
        
        rttr.addFlashAttribute("enroll_result", author.getAuthorName());
        logger.info("rttr :" +  rttr);
        
        return "redirect:/admin/authorManage";
    }
    
	/* �۰� �� ������ */
	@GetMapping({"/authorDetail","/authorModify"})
	public void authorGetInfoGET(int authorId, Criteria cri, Model model) throws Exception {
		
		logger.info("controller::::::authorDetail......." + authorId);
		
		/* �۰� ���� ������ ���� */
		model.addAttribute("cri", cri);
		
		/* ���� �۰� ���� */
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
		
		logger.info("controller authorDetail ::::::model>>>>>>>>"+ authorId);
		
	}
	
	/* �۰� ���� ���� */
	@PostMapping("/authorModify")
	public String authorModifyPOST(AuthorVO author, RedirectAttributes rttr) throws Exception{
		
		logger.info("authorModifyPOST......." + author);
		
		int result = authorService.authorModify(author);
		
		logger.info("controll:: authorModifyResult:::>>>>>" + result);
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/authorManage";
		
	}
	
    /* ��ǰ ��� */
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(BookVO book, RedirectAttributes rttr) {
		
		logger.info("goodsEnrollPOST......" + book);
		
		try {
			adminService.bookEnroll(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rttr.addFlashAttribute("enroll_result", book.getBookName());
		
		return "redirect:/admin/goodsManage";
	}
	
	/* �۰� �˻� �˾�â */
	@GetMapping("/authorPop")
	public void authorPopGET(Criteria cri, Model model) throws Exception{
		
		logger.info("Contorll ::>>authorPopGET>>>>>>>>>>");
		
		cri.setAmount(5);
		
		/* �Խù� ��� ������ */
		List list = authorService.authorGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list); // �۰� ���� ��� 
		}else {
			model.addAttribute("listCheck","empty"); // �۰� ���� XX
		}
		
		/* ������ �̵� �������̽� ������ */
		model.addAttribute("pageMaker",new PageDTO(cri, authorService.authorGetTotal(cri)));
		
	
	}
    
}