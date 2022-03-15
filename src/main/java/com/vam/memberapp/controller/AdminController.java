package com.vam.memberapp.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vam.memberapp.model.Criteria;
import com.vam.memberapp.model.dto.AttachImageVO;
import com.vam.memberapp.model.dto.AuthorVO;
import com.vam.memberapp.model.dto.BookVO;
import com.vam.memberapp.model.dto.CateVO;
import com.vam.memberapp.model.dto.PageDTO;
import com.vam.memberapp.model.service.AdminService;
import com.vam.memberapp.model.service.AuthorService;

import net.coobird.thumbnailator.Thumbnails;

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
			//return;
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
	@GetMapping({"/goodsDetail", "/goodsModify"})
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model, CateVO cateVO ) throws JsonProcessingException, Exception {
		
		logger.info("����ȸ ������ Controller goodsDetail()........." + bookId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		List list = adminService.cateList(cateVO);
		
		String cateList = mapper.writeValueAsString(list);
		/* ī�װ� ����Ʈ ������ */
			model.addAttribute("cateList",cateList);
			logger.info("����ȸ ������ Controller model::::::>>>>>>>>" + model);
		
		/* ��� ������ ���� ���� */
		model.addAttribute("cri", cri);
		
		/* ��ȸ ������ ���� */
		model.addAttribute("goodsInfo", adminService.goodsGetDetail(bookId));
		logger.info("����ȸ ������ Controller goodsInfo::::::>>>>>>>>" + model);
		
	}
	
	/* ��ǰ ���� ���� */
	@PostMapping("/goodsModify")
	public String goodsModifyPOST(BookVO vo, RedirectAttributes rttr) {
		
		logger.info("goodsModifyPOST::::::>>>>>>>>>" + vo);
		
		int result = adminService.goodsModify(vo);
		
		logger.info("goodsModifyPOST:::::: result >>>>>>>>>" + result);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/goodsManage";		
		
	}
	
	/* ��ǰ ���� ���� */
	@PostMapping("/goodsDelete")
	public String goodsDeletePOST(int bookId, RedirectAttributes rttr) {
		
		logger.info("goodsDeletePOST ::::: >>>>>");
		
		// ���� �̹��� ��� ����
		List<AttachImageVO> fileList = adminService.getAttachInfo(bookId);
		// ���� �̹��� ��� ����
		if(fileList != null) {
			
			List<Path> pathList = new ArrayList();
			
			fileList.forEach(vo ->{
				
				// ���� �̹���
				Path path = Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
				pathList.add(path);
				
				// ������ �̹���
				path = Paths.get("C:\\upload", vo.getUploadPath(), "s_" + vo.getUuid()+"_" + vo.getFileName());
				pathList.add(path);
				
			});
			
			pathList.forEach(path ->{
				path.toFile().delete();
			});
			
		}
		
		int result = adminService.goodsDelete(bookId);
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/goodsManage";
		
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
	
	/* �۰� ���� ���� */
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
	
	/* ÷�� ���� ���ε� */
	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {
		//�⺻ for
		/*
		for(int i = 0; i < uploadFile.length; i++) {
			logger.info("-----------------------------------------------");
			logger.info("���� �̸� : " + uploadFile[i].getOriginalFilename());
			logger.info("���� Ÿ�� : " + uploadFile[i].getContentType());
			logger.info("���� ũ�� : " + uploadFile[i].getSize());			
		}
		*/
		
		logger.info("uploadAjaxAction:::>>>>> PostController");
		
		
		/* �̹��� ���� üũ */
		for(MultipartFile multipartFile: uploadFile) {
			
			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;
			
			try {
				type = Files.probeContentType(checkfile.toPath());
				logger.info("MIME TYPE : " + type);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(!type.startsWith("image")) {
				
				List<AttachImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
				
			}
			
		}// for
		
		
		String uploadFolder = "C:\\upload";
		/* ��¥ ���� ��� */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		String datePath = str.replace("-", File.separator);
		
		/* ���� ���� */
		File uploadPath = new File(uploadFolder, datePath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		/* �̹��� ���� ��� ��ü */
		List<AttachImageVO> list = new ArrayList();
		
		// ���� for
		for(MultipartFile multipartFile : uploadFile) {
			logger.info("------------MultipartFile----------------------------------");
			
			/* �̹��� ���� ��ü */
			AttachImageVO vo = new AttachImageVO();
			
			/* ���� �̸� */
			String uploadFileName = multipartFile.getOriginalFilename();			
			vo.setFileName(uploadFileName);
			vo.setUploadPath(datePath);
			
			/* uuid ���� ���� �̸� */
			String uuid = UUID.randomUUID().toString();
			vo.setUuid(uuid);
			
			uploadFileName = uuid + "_" + uploadFileName;
			
			/* ���� ��ġ, ���� �̸��� ��ģ File ��ü */
			File saveFile = new File(uploadPath, uploadFileName);
			
			/* ���� ���� */
			try {
				multipartFile.transferTo(saveFile);
				
				/* ����� ����(ImageIO) */
				/*
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
				
				BufferedImage bo_image = ImageIO.read(saveFile);
				
					 ���� 
					double ratio = 3; 
					 ���� ���� 
					int width = (int)(bo_image.getWidth() / ratio );
					int height = (int)(bo_image.getHeight() / ratio );
					
				BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
								
				Graphics2D graphic = bt_image.createGraphics();
				
				graphic.drawImage(bo_image, 0, 0,width, height, null);
					
				ImageIO.write(bt_image, "jpg", thumbnailFile);
				*/
				
				/* ��� 2 */
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);	
				
				BufferedImage bo_image = ImageIO.read(saveFile);

					//���� 
					double ratio = 3;
					//���� ����
					int width = (int) (bo_image.getWidth() / ratio);
					int height = (int) (bo_image.getHeight() / ratio);					
				
				Thumbnails.of(saveFile)
		        .size(width, height)
		        .toFile(thumbnailFile);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			list.add(vo);
		} // for �� 
		
		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
		
		return result;
	}
	
	/* �̹��� ���� ���� */
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		
		logger.info("deleteFile:::::>>>>> " + fileName);
		
		File file = null;
		
		try {
			/* ����� ���� ���� */
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			/* ���� ���� ���� */
			String originFileName = file.getAbsolutePath().replace("s_", "");
			
			logger.info("originFileName : " + originFileName);
			
			file = new File(originFileName);
			
			file.delete();
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
			
		} // catch
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	
	
}