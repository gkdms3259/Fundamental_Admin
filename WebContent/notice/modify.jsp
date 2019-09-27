<%@page import="kr.co.kic.dev1.dto.NoticeDto"%>
<%@page import="kr.co.kic.dev1.dao.NoticeDao"%>
<%@ page pageEncoding="utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
	int num = Integer.parseInt(request.getParameter("num"));
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");	
	
	NoticeDao dao = NoticeDao.getInstance();
	NoticeDto dto =  new NoticeDto(writer,title,content);
	dto.setNum(num);
	boolean isSuccess = dao.update(dto);
	if(isSuccess){
%>
	<script>
		alert("성공");
		location.href="view.jsp?num=<%=num%>";
	</script>
	<%}else {%>
	<script>
		alert("실패");
		history.back(-1);
	</script>
	<%} %>
	
