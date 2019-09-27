<%@page import="kr.co.kic.dev1.dao.MemberDao"%>
<%@page import="kr.co.kic.dev1.dto.MemberDto"%>
<%@ page pageEncoding="utf-8" %>

<%
	request.setCharacterEncoding("utf-8");
	int seq = Integer.parseInt(request.getParameter("seq"));
	int cPage = Integer.parseInt(request.getParameter("page"));
	MemberDao dao = MemberDao.getInstance();
	boolean isSuccess = dao.delete(seq);
	if(isSuccess){
%>
	<script>
		alert('삭제성공');
		location.href="list.jsp?page=<%=cPage%>";
	</script>		
<%}else{ %>	
	<script>
		alert('삭제실패');
		history.back(-1);
	</script>
<% } %>