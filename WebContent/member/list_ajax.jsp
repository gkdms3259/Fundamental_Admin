<%@page import="kr.co.kic.dev1.dto.MemberDto"%>
<%@page import="kr.co.kic.dev1.dao.MemberDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="utf-8"%>
<%
int cPage = Integer.parseInt(request.getParameter("page"));
int length = Integer.parseInt(request.getParameter("length"));;
int start = (cPage-1) * length;
MemberDao dao = MemberDao.getInstance();
ArrayList<MemberDto> list = dao.select(start,length);

	if (list.size() != 0) {
		for (int i = 0; i < list.size(); i++) {
			MemberDto dto = list.get(i);
			int seq = dto.getSeq();
			String id = dto.getId();
			String name = dto.getName();
			String email = dto.getEmail();
			String phone = dto.getPhone();
			String regdate = dto.getRegdate();
%>
<tr>
	<th scope="row"><%=seq%></th>
	<td><%=name%></td>
	<td><a href="view.jsp?seq=<%=seq%>&page=<%=cPage%>"><%=id%></a></td>
	<td><%=email%></td>
	<td><%=phone%></td>
	<td><%=regdate%></td>
</tr>
<%
	}
	} else {
%>
<tr>
	<td colspan="6">회원정보가 없습니다.</td>
</tr>
<%
	}
%>