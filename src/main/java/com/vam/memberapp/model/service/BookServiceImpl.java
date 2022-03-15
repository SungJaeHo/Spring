package com.vam.memberapp.model.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dao.AttachDaoImpl;
import com.vam.memberapp.model.dao.BookDao;
import com.vam.memberapp.model.dto.AttachImageVO;
import com.vam.memberapp.model.dto.BookVO;
import com.vam.memberapp.model.dto.CateVO;

@Service
public class BookServiceImpl implements BookService{
	
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private AttachDaoImpl attachDaoImpl;
	
	/* »óÇ° °Ë»ö */
	@Override
	public List<BookVO> getGoodsList(Criteria cri) {
		
		logger.info("BookServiceImpl+++getGoodsList()....>>>>>>>>>>" + cri);
		
		String type = cri.getType();
		String[] typeArr = type.split("");
		String author = sqlSession.selectOne("BOOK.getAuthorIdList",cri.getKeyword());
		
		String[] authorArr = author.split(",");
		
		if(type.equals("A") || type.equals("AC") || type.equals("AT") || type.equals("ACT")) {
			if(authorArr.length == 0) {
				return new ArrayList();
			}
		}
		for(String t : typeArr) {
			if(t.equals("A")) {
				cri.setAuthorArr(authorArr);
			}
		}
		
		List<BookVO> list = bookDao.getGoodsList(cri);
		
		list.forEach(book -> {
			
			int bookId = book.getBookId();
			
			List<AttachImageVO> imageList = attachDaoImpl.getAttachList(bookId);
			
			book.setImageList(imageList);
			
		});
		
		return list;
	}

	/* »çÇ° ÃÑ °¹¼ö */
	@Override
	public int goodsGetTotal(Criteria cri) {
		
		logger.info("BookServiceImpl+++goodsGetTotal()......." + cri );
		
		return bookDao.goodsGetTotal(cri);
		
	}
	
	@Override
	public List<CateVO> getCateCode1() {
		logger.info("BookServiceImpl+++getCateCode1().......");
		return bookDao.getCateCode1();
	}
	
	@Override
	public List<CateVO> getCateCode2() {
		logger.info("BookServiceImpl+++getCateCode2().......");
		return bookDao.getCateCode2();
	}
	
}
