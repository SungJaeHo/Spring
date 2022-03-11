package com.vam.memberapp.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.memberapp.model.dto.MemberVO;
import com.vam.memberapp.model.service.MemberService;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberservice;

	@Autowired
	private JavaMailSender mailSender;
	
	//��й�ȣ ������ ���� security-context
	@Autowired
	private BCryptPasswordEncoder pwEncoder;

	// ȸ������ ������ �̵�
	@RequestMapping(value = "/member/join", method = RequestMethod.GET)
	public void loginGET() {
		logger.info("ȸ������ ������ ����");
	}

	// ȸ������ ��ư ������ �۵�
	@RequestMapping(value = "/member/join", method = RequestMethod.POST)
	public String joinPOST(MemberVO member) throws Exception {

		logger.info("join ����");

		// ȸ������ ���� ����
        String rawPw = ""; // ���ڵ� �� ��й�ȣ
        String encodePw = ""; // ���ڵ� �� ��й�ȣ
        
        rawPw = member.getMemberPw();  // ��й�ȣ ������ ����
        encodePw = pwEncoder.encode(rawPw); // ��й�ȣ ���ڵ�
        member.setMemberPw(encodePw); // ���ڵ��� ��й�ȣ member��ü�� �ٽ� ����
        
        /* ȸ������ ���� ���� */
        memberservice.memberJoin(member);

		logger.info("join Service ����");

		return "redirect:/member/main";

	}
	
	//���� ������ �̵�
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public void mainPageGET() {
		logger.info("���� ������ ����");
		
	}
	// �α��� ������ �̵�
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public void joinGET() {
		logger.info("�α��� ������ ����");
	}

	// ���̵� �ߺ� �˻�
	@RequestMapping(value = "/member/memberIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String memberIdChkPOST(String memberId, HttpServletResponse response) throws Exception {
		logger.info("memberIdChk() ����");
		int result = memberservice.idCheck(memberId);
		logger.info("����� = " + result);
		if(result != 0) {
			return "fail";	// �ߺ� ���̵� ����
		} else {
			return "success";	// �ߺ� ���̵� x
		}	

	} // memberIdChkPOST() ����

	
    /* �̸��� ���� */
    @RequestMapping(value="/member/mailCheck", method=RequestMethod.GET)
    @ResponseBody
    public String mailCheckGET(String email) throws Exception{
        
        /* ��(View)�κ��� �Ѿ�� ������ Ȯ�� */
        logger.info("�̸��� ������ ���� Ȯ��");
        logger.info("������ȣ : " + email);
        
        /* ������ȣ(����) ���� */
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;
        logger.info("���� ��ȣ ���� " + checkNum);
        
        /* �̸��� ������ */
        String setFrom = "kozz153@naver.com";
        String toMail = email;
        String title = "ȸ������ ���� �̸��� �Դϴ�.";
        String content =
        		"����ȣ ������ ������ �������� �湮�� �ּż� �����մϴ�." +
        		"<br><br>" + 
        		"���� ��ȣ�� " + checkNum + "�Դϴ�." +
        		"<br>" +
        		"�ش� ������ȣ�� ������ȣ Ȯ�ζ��� �����Ͽ� �ּ���.";
        
        try {
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        String num = Integer.toString(checkNum);
        
        return num;
    }
    
    /* �α��� */
    @RequestMapping(value="/member/login", method=RequestMethod.POST)
    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
/*        //System.out.println("login �޼��� ����");
        System.out.println("���޵� ������ : " + member);
    	HttpSession session = request.getSession();
    	MemberVO lvo = memberservice.memberLogin(member);
    	System.out.println("MemberController memberVO : >>>>" + lvo);
    	 if(lvo == null) {  // ��ġ���� �ʴ� ���̵�, ��й�ȣ �Է� ���
             int result = 0;
             rttr.addFlashAttribute("result", result);
             System.out.println("������ ���" + result);
             return "redirect:/member/login";
         }
         session.setAttribute("member", lvo);  // ��ġ�ϴ� ���̵�, ��й�ȣ ��� (�α��� ����)
         return "redirect:/member/main";*/
        HttpSession session = request.getSession();
        String rawPw = "";
        String encodePw = "";
    
        MemberVO lvo = memberservice.memberLogin(member);    // �����Ѿ��̵�� ��ġ�ϴ� ���̵� �ִ��� 
        
        System.out.println("MemberController memberVO : >>>>" + lvo);
        if(lvo != null) {            // ��ġ�ϴ� ���̵� �����
            
            rawPw = member.getMemberPw();        // ����ڰ� ������ ��й�ȣ
            encodePw = lvo.getMemberPw();        // �����ͺ��̽��� ������ ���ڵ��� ��й�ȣ
            
            if(true == pwEncoder.matches(rawPw, encodePw)) {        // ��й�ȣ ��ġ���� �Ǵ�
                lvo.setMemberPw("");                    // ���ڵ��� ��й�ȣ ���� ����
                session.setAttribute("member", lvo);     // session�� ������� ���� ����
                return "redirect:/member/main";        // ���������� �̵�
            } else {
 
                rttr.addFlashAttribute("result", 0);            
                return "redirect:/member/login";    // �α��� �������� �̵�
            }
        } else {                    // ��ġ�ϴ� ���̵� �������� ���� �� (�α��� ����)
            
            rttr.addFlashAttribute("result", 0);            
            return "redirect:/member/login";    // �α��� �������� �̵�
            
        }
    }
    /* ���������� �α׾ƿ� */
    @RequestMapping(value="/member/logout", method=RequestMethod.GET)
    public String logoutMainGET(HttpServletRequest request) throws Exception{
    	
        logger.info("logoutMainGET�޼��� ����");
        
        HttpSession session = request.getSession();
        
        session.invalidate();
        
        return "redirect:/member/main";  
    }
    /* �񵿱��� �α׾ƿ� �޼��� */
    @RequestMapping(value="/member/logout", method=RequestMethod.POST)
    @ResponseBody
    public void logoutPOST(HttpServletRequest request) throws Exception{
        
        logger.info("�񵿱� �α׾ƿ� �޼��� ����");
        
        HttpSession session = request.getSession();
        
        session.invalidate();
        
    }
}
