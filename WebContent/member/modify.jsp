<%@page import="kr.co.kic.dev1.dao.MemberDao"%>
<%@page import="kr.co.kic.dev1.dto.MemberDto"%>
<%@ page pageEncoding="utf-8" %>

<%
	request.setCharacterEncoding("utf-8");
	int seq = Integer.parseInt(request.getParameter("seq"));
	int cPage = Integer.parseInt(request.getParameter("page"));
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	MemberDto dto = new MemberDto(seq,id,email,name,phone);
	MemberDao dao = MemberDao.getInstance();
	boolean isSuccess = dao.update(dto);
	if(isSuccess){
%>
	<script>
		alert('수정성공');
		location.href="view.jsp?seq=<%=seq%>&page=<%=cPage%>";
	</script>		
<%}else{ %>	
	<script>
		alert('수정실패');
		history.back(-1);
	</script>
<% } %>