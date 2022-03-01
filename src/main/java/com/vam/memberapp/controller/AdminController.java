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

import com.fasterxml.jackson.core.JsonProcessingException;
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
	
    /* 관리자 메인 페이지 이동 */
    @RequestMapping(value="/main", method = RequestMethod.GET)
    public void adminMainGET() throws Exception{
        
        logger.info("관리자 페이지 이동");
        
    }
    
    /* 상품 등록 페이지 접속 */
    @RequestMapping(value = "/goodsManage", method = RequestMethod.GET)
    public void goodsManageGET(Criteria cri, Model model) throws Exception{
        logger.info("상품 관리(상품목록) 페이지 접속");
        
		/* 상품 리스트 데이터 */
		List list = adminService.goodsGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		logger.info("상품 관리(상품목록) 페이지 접속"+ list);
		/* 페이지 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));
        
    }
    
    /* 상품 등록 페이지 접속 */
    @RequestMapping(value = "/goodsEnroll", method = RequestMethod.GET)
    public void goodsEnrollGET(Model model , CateVO cateVO) throws Exception{
        
    	logger.info("상품 등록 페이지 접속");
        
    	ObjectMapper objm = new ObjectMapper();
    	
        List list = adminService.cateList(cateVO);
        
        String cateList = objm.writeValueAsString(list);
        
        model.addAttribute("cateList",cateList);
        
        logger.info("변경전 ::::::>>>>>>>>>" + list );
        logger.info("변경후 ::::::>>>>>>>>>" + cateList);
        
    }
    
	/* 상품 조회 페이지 */
	@GetMapping({"/goodsDetail", "/goodsModify"})
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model, CateVO cateVO ) throws JsonProcessingException, Exception {
		
		logger.info("상세조회 페이지 Controller goodsDetail()........." + bookId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		List list = adminService.cateList(cateVO);
		
		String cateList = mapper.writeValueAsString(list);
		/* 카테고리 리스트 데이터 */
			model.addAttribute("cateList",cateList);
			logger.info("상세조회 페이지 Controller model::::::>>>>>>>>" + model);
		
		/* 목록 페이지 조건 정보 */
		model.addAttribute("cri", cri);
		
		/* 조회 페이지 정보 */
		model.addAttribute("goodsInfo", adminService.goodsGetDetail(bookId));
		logger.info("상세조회 페이지 Controller goodsInfo::::::>>>>>>>>" + model);
		
	}
	
	/* 상품 정보 수정 */
	@PostMapping("/goodsModify")
	public String goodsModifyPOST(BookVO vo, RedirectAttributes rttr) {
		
		logger.info("goodsModifyPOST::::::>>>>>>>>>" + vo);
		
		int result = adminService.goodsModify(vo);
		
		logger.info("goodsModifyPOST:::::: result >>>>>>>>>" + result);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/goodsManage";		
		
	}
	
	/* 상품 정보 삭제 */
	@PostMapping("/goodsDelete")
	public String goodsDeletePOST(int bookId, RedirectAttributes rttr) {
		
		logger.info("goodsDeletePOST ::::: >>>>>");
		
		int result = adminService.goodsDelete(bookId);
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/goodsManage";
		
	}
    
    /* 작가 등록 페이지 접속 */
    @RequestMapping(value = "/authorEnroll", method = RequestMethod.GET)
    public void authorEnrollGET() throws Exception{
        logger.info("작가 등록 페이지 접속");
    }
    
    /* 작가 관리 페이지 접속 */
    @RequestMapping(value = "/authorManage", method = RequestMethod.GET)
    public void authorManageGET(Criteria cri , Model model) throws Exception{
        logger.info("작가 관리 페이지 접속:::::::>>>>>" + cri );
        
        /* 작가 목록 출력 데이터 */
        List list = authorService.authorGetList(cri);
        
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	// 작가 존재 경우
		} else {
			model.addAttribute("listCheck", "empty");	// 작가 존재하지 않을 경우
		}
        
        /* 페이지 이동 인터페이스 데이터*/
        int total = authorService.authorGetTotal(cri);
        
        PageDTO pageMaker = new PageDTO(cri, total);
        
        model.addAttribute("pageMaker", pageMaker);
        
        /* 페이지 이동 인터페이스 데이터 */
        model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));
    }    
    
    @RequestMapping(value="/authorEnroll", method = RequestMethod.POST)
    public String authorEnrollPOST(AuthorVO author,HttpServletRequest request, RedirectAttributes rttr) throws Exception{
    	 
    	 
        logger.info("authorEnroll :" +  author);
        
        authorService.authorEnroll(author);      // 작가 등록 쿼리 수행
        
        rttr.addFlashAttribute("enroll_result", author.getAuthorName());
        logger.info("rttr :" +  rttr);
        
        return "redirect:/admin/authorManage";
    }
    
	/* 작가 상세 페이지 */
	@GetMapping({"/authorDetail","/authorModify"})
	public void authorGetInfoGET(int authorId, Criteria cri, Model model) throws Exception {
		
		logger.info("controller::::::authorDetail......." + authorId);
		
		/* 작가 관리 페이지 정보 */
		model.addAttribute("cri", cri);
		
		/* 선택 작가 정보 */
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
		
		logger.info("controller authorDetail ::::::model>>>>>>>>"+ authorId);
		
	}
	
	/* 작가 정보 수정 */
	@PostMapping("/authorModify")
	public String authorModifyPOST(AuthorVO author, RedirectAttributes rttr) throws Exception{
		
		logger.info("authorModifyPOST......." + author);
		
		int result = authorService.authorModify(author);
		
		logger.info("controll:: authorModifyResult:::>>>>>" + result);
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/authorManage";
		
	}
	
	/* 작가 정보 삭제 */
	@PostMapping("/authorDelete")
	public String authorDeletePOST(int authorId, RedirectAttributes rttr) {
		
		logger.info("authorDeletePOST..........");
		
		int result = 0;
		
		try {
			
			result = authorService.authorDelete(authorId);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result = 2;
			rttr.addFlashAttribute("delete_result", result);
			
			return "redirect:/admin/authorManage";
			
		}
		
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/authorManage";
		
	}	
	
    /* 상품 등록 */
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
	
	/* 작가 검색 팝업창 */
	@GetMapping("/authorPop")
	public void authorPopGET(Criteria cri, Model model) throws Exception{
		
		logger.info("Contorll ::>>authorPopGET>>>>>>>>>>");
		
		cri.setAmount(5);
		
		/* 게시물 출력 데이터 */
		List list = authorService.authorGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list); // 작가 존재 경우 
		}else {
			model.addAttribute("listCheck","empty"); // 작가 존재 XX
		}
		
		/* 페이지 이동 인터페이스 데이터 */
		model.addAttribute("pageMaker",new PageDTO(cri, authorService.authorGetTotal(cri)));
		
	
	}
    
}