package com.vam.memberapp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dao.AttachDaoImpl;
import com.vam.memberapp.model.dto.AttachImageVO;
import com.vam.memberapp.model.dto.BookVO;
import com.vam.memberapp.model.dto.PageDTO;
import com.vam.memberapp.model.service.BookService;

@Controller
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private AttachDaoImpl attachDaoImpl; 
	
	@Autowired
	private BookService bookService;
	
	//메인 페이지 이동
	@GetMapping(value = "/member/main")
	public void mainPageGET(Model model) {
		logger.info("메인 페이지 진입");
		model.addAttribute("cate1" , bookService.getCateCode1());
		model.addAttribute("cate2", bookService.getCateCode2());
	}
	
    @GetMapping("/display")
    public ResponseEntity<byte[]> getImage(String fileName){
    	logger.info("getImage();:::::>>>>>>>" + fileName);
    	
    	File file = new File("C:\\upload\\" + fileName);
    	
		ResponseEntity<byte[]> result = null;
		
		try {
			
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
    }
    
	/* 이미지 정보 반환 */
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> getAttachList(int bookId){
		
		logger.info("getAttachList.........." + bookId);
		
		return new ResponseEntity<List<AttachImageVO>>(attachDaoImpl.getAttachList(bookId), HttpStatus.OK);
		
	}
	
	/* 상품 검색 */
	@GetMapping(value="/search")
	public String searchGoodsGET(Criteria cri, Model model) {
		
		logger.info("cri 1 :::>>>>" + cri);
		
		List<BookVO> list = bookService.getGoodsList(cri);
		logger.info("pre list 2 ::>>>" + list);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			logger.info("list 3 ::::>>>> "+ list);
		}else {
			model.addAttribute("listcheck" , "empty");
			return "/search";
		}
		
		int total = bookService.goodsGetTotal(cri);
		logger.info("total ::::>>>> "+ list);
		
		PageDTO pageMaker = new PageDTO(cri, total);
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "/search";
		
	}
	
	
	
}
