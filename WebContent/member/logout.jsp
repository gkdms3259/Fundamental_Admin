<%@ page pageEncoding="utf-8"%>
<% 
	/*
		HTTPSession 객체를 종료하는 3가지 방법
		1. 브라우저를 종료했을 경우.
		2. 해당 페이지의 시간이 30분 경과되면 자동종료
		3 invalidate() 메서드를 호출하면 
	*/
	session.invalidate(); //세션 강제로 날리기.
	response.sendRedirect("/");
	
%>
