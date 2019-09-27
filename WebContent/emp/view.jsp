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
									<input type="text" class="form-control" name="writer" id="writer" placeholder="이름을 입력해 주세요" value="<%=writer %>">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-form-label col-sm-2" for="title">제목</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요" value="<%=title %>">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-form-label col-sm-2" for="content">내용</label>
								<div class="col-sm-10">
									<textarea class="form-control" name="content" id="content" rows="10" placeholder="내용을 입력해 주세요" ><%=content %></textarea>
								</div>
							</div>
							<input type="hidden" name="num" value="<%=num %>"/>
						</form>

						<div class="text-right">
							<a href="#" id="modifyNotice" class="btn btn-outline-primary">수정</a>
							<a href="#" id="deleteNotice"class="btn btn-outline-danger">삭제</a>
							<a href="list.jsp" class="btn btn-outline-success">리스트</a>
						</div>
						<script>
							$(function(){ 
								$("#modifyNotice").on('click',function(event){
									event.preventDefault();
									//유효성 검사
									f.action="modify.jsp";
									f.submit();
								});
								$("#deleteNotice").on('click',function(event){
									event.preventDefault();
									//유효성 검사 필요 없음
									f.action="delete.jsp"
									f.submit();
								});
							});
						</script>
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
  
