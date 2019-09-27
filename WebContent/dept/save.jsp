<%@page import="kr.co.kic.dev1.dto.NoticeDto"%>
<%@page import="kr.co.kic.dev1.dao.NoticeDao"%>
<%@ page pageEncoding="utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");	
	
	NoticeDao dao = NoticeDao.getInstance();
	NoticeDto dto =  new NoticeDto(writer,title,content);
	boolean isSuccess = dao.insert(dto);
	if(isSuccess){
%>
	<script>
		alert("성공");
		location.href="list.jsp";
	</script>
	<%}else {%>
	<script>
		alert("실패");
		history.back(-1);
	</script>
	<%} %>
	
	<%=writer%> , <%=title%>, <%=content%>