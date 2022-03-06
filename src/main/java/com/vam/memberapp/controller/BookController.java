package com.vam.memberapp.controller;

import java.io.File;


import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vam.memberapp.model.dao.AttachDaoImpl;
import com.vam.memberapp.model.dto.AttachImageVO;

@Controller
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private AttachDaoImpl attachDaoImpl; 
	
/*	//메인 페이지 이동
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public void mainPageGET() {
		
		logger.info("메인 페이지 진입");
		
	}*/
	
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
}
