<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 양식</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/member.css" />
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="wrap">
		<div class="memberBox">
			<div class="join">
				<form action=""
					method="post">
					<!-- 			       onsubmit="return validate();"> -->
					<span class="sector-title">회원가입</span>
					<div class="join-info">
						<p>* id</p>
						<button type="button" class="btn_id-check" onclick="xmlIdCheck()">ID
							확인</button>
						<input type="text" name="memberId" id="memberId" class="join-text"
							size="10" /> <span id="id-check-msg" class="id-check-msg"></span>
					</div>
					<div class="join-info">
						<p>* password</p>
						<input type="password" name="memberPw" id="memberPw"
							class="join-text" size="10" />
					</div>
					<div class="join-info">
						<p>* email</p>
						<input type="text" name="memberMail" id ="memberMail" class="join-text" size="10" />
					</div>
					<div class="join-info">
						<p>* 주소</p>
						<input type="text" name="memberAddr2" id ="memberAddr2" class="join-text" size="10" />
					</div>
					<button type="submit" class="btn-join-submit">전송</button>
				</form>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			//회원가입 버튼(회원가입 기능 작동)
			$(".btn-join-submit").click(function() {
				$("#join_form").attr("action", "/member/join");
				$("#join_form").submit();
			});
		});
	</script>
	<%-- 	<script type="text/javascript">
		var ajaxFlag = false;
		function validate() {
	        var pass = document.getElementById('password');
	        var regExpPw = /(?=.*\d)(?=.*[~`!@#$%\^&*()-+=])(?=.*[a-zA-Z]).{8,15}$/;

	        function chk(re, e, msg) {
	            if(re.test(e.value)) {           		
	                return true;          
		    	}else{
	           	  alert(msg);
	              e.value = "";
	              e.focus();
	              //기본 이벤트 취소
	              return false;
	            }
	     	}

	        if(!ajaxFlag){
	        	alert("아이디 중복검사를 해주세요");
	        	return false;
	        }
	       
	        // 비밀번호 검사
	      	// 암호는 영문자와 숫자로 8글자이상  기호문자 한개이상 8글자 이상
	        if(!chk(regExpPw, pass,'비밀번호 숫자,영어,특수문자가 하나 이상 포함, 8글자 이상 15글자 이하')){
	        	return false;
	        }

	        return true;
	    }
		
		
		function xmlIdCheck(){
			//사용자가 입력한 id값을 받아온다.
			var id = document.querySelector('#userId').value;
			//XMLHttpRequest 객체 생성
			var xhr = new XMLHttpRequest();
			//http request message의 시작줄 작성
			xhr.open('POST','<%=request.getContextPath()%>/member/idcheck.do');
			//http request message의 header 작성
			xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			//body에 데이터 작성하고 전송
			xhr.send('userId='+id);
			//ajax 통신이 끝나고(load) 실행할 콜백함수 등록
			xhr.addEventListener('load',function(){
				//response body 있는 데이터를 받아옴
				var data = xhr.response;
				console.dir(data);
				if(data == ''){
					ajaxFlag = true;
					document.querySelector('#id-check-msg').textContent = '사용 가능한 아이디 입니다.';
				}else{
					document.querySelector('#id-check-msg').textConten = data + "는 이미 존재하는 아이디 입니다.";
					ajaxFlag = false;
					
				}
			})
		}
		
	</script> --%>

</body>
</html>
