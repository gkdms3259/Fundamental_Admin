<%@page import="kr.co.kic.dev1.dto.NoticeDto"%>
<%@page import="kr.co.kic.dev1.dao.NoticeDao"%>
<%@ page pageEncoding="UTF-8" info="test"%>
<%
	String tempNum = request.getParameter("num");
	int num = 0;
	try{
	num = Integer.parseInt(tempNum);
	}catch(NumberFormatException e){
		num=0;
	}
	NoticeDao dao = NoticeDao.getInstance();
	NoticeDto dto = dao.select(num);
	
	if(dto != null){
		num=dto.getNum();
		String writer = dto.getWriter();
		String title = dto.getTitle();
		String content = dto.getContent();
		String regdate = dto.getRegdate();
%>
<%@ include file = "../inc/header.jsp" %>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb justify-content-end">
			<li class="breadcrumb-item"><a href="/">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Notice</li>
		</ol>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-sm-12"> 
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">공지사항</h5>

						<form class="form-horizontal" role="form" name="f" method="post" action="">
							<div class="form-group row">
								<label class="col-form-label col-sm-2" for="writer">작성자</label>
								<div class="col-sm-10">
									<p><%=writer %></p>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-form-label col-sm-2" for="title">제목</label>
								<div class="col-sm-10">
									<p><%=title %></p>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-form-label col-sm-2" for="content">내용</label>
								<div class="col-sm-10">
									<p><%=content.replaceAll("\n","<br>") %></p>
								</div>
							</div>
						</form>

						<div class="text-right">
							<a href="list.jsp" class="btn btn-outline-success">리스트</a>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
			<%@ include file = "../inc/footer.jsp" %>
			<%}else{%>
  	<script>
	  	alert("없어~!!");
  		history.back(-1);
  	</script>
  <%}%>
  
