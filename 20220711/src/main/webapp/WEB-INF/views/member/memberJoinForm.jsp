<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<div>
			<h1>회 원 가 입</h1>
		</div>
		<div>
			<form id="frm" action="memberJoin.do" onsubmit="return formCheck()"
				method="post">
				<div>
					<table border="1">
						<tr>
							<th width="150">아이디</th>
							<td width="300"><input type="text" id="memberId"
								name="memberId" size="20"> &nbsp; <input type="hidden"
								id="checkId" value="No">
								<button type="button" id="btn" onclick="idCheck()">중복체크</button>
							</td>
						</tr>
						<tr>
							<th width="150">패스워드</th>
							<td width="300"><input type="password" id="memberPassword"
								name="memberPassword" size="20"> &nbsp;</td>
						</tr>
						<tr>
							<th width="150">패스워드 확인</th>
							<td width="300"><input type="password" id="password"
								size="20"> &nbsp;
						</tr>
						<tr>
							<th width="150">이름</th>
							<td width="300"><input type="text" id="memberName"
								name="memberName" size="20"> &nbsp;</td>
						</tr>
					</table>
				</div>
				<br>
				<div>
					<input type="submit" value="회원가입">&nbsp;&nbsp;&nbsp; <input
						type="reset" value="취소">&nbsp;&nbsp;&nbsp; <input
						type="button" value="홈으로" onclick="location.href='main.do'">
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function formCheck() {
			if (frm.memberId.value == "") {
				alert("사용자 아이디를 입력하세요.");
				frm.memberId.focus();
				return false;
			}

			if (frm.checkId.value == "No") {
				alert("아이디 중복체크를 해주세요.");
				return false;
			}

			if (frm.memberPassword.value != frm.password.value) {
				alert("패스워드가 일피하지 않습니다.");
				frm.memberPassword.value = "";
				frm.password.value = "";
				from.memberPassword.foucus();
				return false;
			}

			if (frm.memberNam.value == "") {
				alert("사용자 이름을 입력하세요.");
				frm.memberName.focus();
				return false;
			}

			return true;
		}

		function idCheck() {
			let id = frm.memberId.value;
			if (id == "") {
				alert("아이디 입력 후 중복체크해주세요.");
				frm.memberId.focus();
			} else {
				// ajax를 이용하여 아이디 중복체크를 수행
				const xht = new XMLHttpRequest(); //ajax 객체 생성
				xht.onload = function() { // xht가 로드될 때 결과를 받아 처리하는 함수
					
					if (this.readyState == 4 && this.status == 200) {
						//console.log(this.responseText);
						htmlConvertAjax(this.responseText); // 성공했을 때 수행하는 함수
					} else {
						errorAjaxCall(); // 실패했을 때 수행하는 함수
					}
				}
				xht.open("GET", "ajaxMemberIdCheck.do?id=" + id); // 호출할 주소와 방식 설정
				xht.send(); // 호출

			}

		}

		function htmlConvertAjax(str) {
			if (str == "Usable") {
				
				alert("사용가능한 아이디 입니다.");
				frm.checkId.value = "Yes";
				frm.btn.disabled = true; // 버튼 비활성화
				frm.memberPassword.focus();
			} else {
				alert("이미 사용중인 아이디입니다.");
				frm.memberId.value = "";
				frm.memberId.focus();
			}
		}

		function errorAjaxCall() {
			alert("네트워크 통신장애로 인해 처리할 수 없습니다. /n 잠시 후 다시 해보세요.");
		}
	</script>
</body>
</html>