<%@page import="kr.co.kic.dev1.dao.MemberDao"%>
<%@ page contentType="application/json;charset=utf-8"%>
<%
	String id = request.getParameter("id");
	MemberDao dao = MemberDao.getInstance();
	boolean isExisted = dao.isCheck(id);
	if(isExisted){
%>
{"result" : "ok"}   <%-- result는 임의로 정한 이름, 있으면 ok, 없으면 fail --%>
<%}else{%>
{"result" : "fail"}		
<%}%>