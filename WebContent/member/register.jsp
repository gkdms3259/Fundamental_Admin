<%@ page pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<%
String tempPage = request.getParameter("page");

	int cPage = 0;
	if(tempPage == null || tempPage.length()==0){
		cPage = 1;
	}
	try{
		cPage = Integer.parseInt(tempPage);
	}catch(NumberFormatException e){
		cPage = 1;
	}
%>
<nav aria-label="breadcrumb">
	<ol class="breadcrumb justify-content-end">
		<li class="breadcrumb-item"><a href="/">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Member</li>
	</ol>
</nav>
<div class="container">
	<div class="row">
		<div class="col-sm-12"> 
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">회원가입</h5>
					<form class="form-horizontal" role="form" name ="f" method="post" action="save.jsp">
						<div class="form-group">
							<label class="col-form-label" for="name">성명</label> 
							<input type="text" class="form-control" name="name" id="name" placeholder="이름을 입력해 주세요">
							<div id = "nameMessage"></div>
						</div>
						<div class="form-group">
							<label class="col-form-label" for="id">아이디</label> 
							<input type="text" class="form-control" name="id" id="id" placeholder="아이디를 입력해 주세요">
							<div id = "idMessage"></div>
						</div>
						<div class="form-group">
							<label for="email">이메일 주소</label>
							<input type="email" class="form-control" name="email" id="email" placeholder="이메일 주소를 입력해주세요">
							<div id = "emailMessage"></div>
						</div>
						
						<div class="form-group">
							<label for="pwd">비밀번호</label> 
							<input type="password" class="form-control" name="pwd" id="pwd" placeholder="비밀번호를 입력해주세요">
							<div id = "pwdMessage"></div>
						</div>
						<div class="form-group">
							<label for="repwd">비밀번호 확인</label> 
							<input type="password" class="form-control" name="repwd" id="repwd" placeholder="비밀번호 확인을 위해 다시한번 입력 해 주세요">
							<div id = "repwdMessage"></div>
						</div>
					
						<div class="form-group">
							<label for="phone">휴대폰 번호</label> 
							<input type="tel" class="form-control" name="phone" id="phone" placeholder="휴대폰번호를 입력해 주세요">
							<div id = "phoneMessage"></div>
						</div>
						<%--
						<div class="form-group text-center">
							<button type="submit" id="join-submit" class="btn btn-primary">
								회원가입<i class="fa fa-check spaceLeft"></i>
							</button>
							<button type="reset" class="btn btn-warning">
								가입취소<i class="fa fa-times spaceLeft"></i>
							</button>
						</div>
						--%>
						<input type = "hidden" name="checkId" id="checkId" value="no"/>
					</form>
						<div class="text-right">
						<a href=" " id="saveMember" class="btn btn-outline-primary">등록</a> 
						<a href="list.jsp?page=<%=cPage%>" class="btn btn-outline-success">리스트</a>
					</div>
					<script>
						$(function(){
							$("#saveMember").on('click',function(event){
								event.preventDefault();
								//유효성 검사
								if($("#name").val().length==0){
									$("#nameMessage").html("<span class='text-danger'>이름을 입력하세요.</span>");  //밑에 빨간색으로 뜨는 경고창.
									$("#name").addClass("is-invalid");  //테두리 빨간색으로.(is-invalid)
									$("#name").focus();  
									return;   //등록이 안되게 다시 return 시킴 (없으면 경고창 뜨고 등록되어버림)
								}
								
								if($("#id").val().length<4 || $("#id").val().length >12){
									$("#idMessage").html("<span class='text-danger'>아이디를 4자이상 12자이하로 입력해주세요.</span>");
									$("#id").addClass("is-invalid"); 
									$("#id").focus();  
									return;
								}
								if($("#checkId").val()=="no"){
									return;
								}
								
								let regEmail = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;   //^는 이러한것부터 시작해야한다라는 뜻. @와 .은 반드시 들어가야한다.
								if(regEmail.test($("#email").val())==false){
									$("#emailMessage").html("<span class='text-danger'>올바른 이메일 형식이 아닙니다.</span>");
									$("#email").addClass("is-invalid");
									$("#email").focus();
									return;
								}
								if($("#checkEmail").val()=="no"){
									return;
								}
								
								if($("#pwd").val().length<6 || $("#pwd").val().length >15){
									$("#pwdMessage").html("<span class='text-danger'>비밀번호를 6자이상 15자이하로 입력해주세요.</span>");
									$("#pwd").addClass("is-invalid"); 
									$("#pwd").focus();  
									return;
								}
								
								
								if($("#repwd").val()!= $("#pwd").val()){
									$("#repwdMessage").html("<span class='text-danger'>비밀번호를 같게 재입력 해주세요.</span>");
									$("#repwd").addClass("is-invalid"); 
									$("#repwd").focus();
									return;
								}
								
	<%--모르겠음 ㅠㅠ--%>			
								if($("#phone").val().length != 11){
									$("#phoneMessage").html("<span class='text-danger'> -를 제외하고 휴대폰 번호를 적어주세요. </span>");
									$("#phone").addClass("is-invalid"); 
									$("#phone").focus();  
									return;
								}
							
								
								f. submit();
						
							}); //end of register click event
							
							$("#name").on("keyup",function(){
								$("#name").removeClass("is-invalid");  //이름 입력하면 경고문구 사라지게 하는것.
								$("#nameMessage").html('');            
							});
							
							$("#id").on("keyup",function(){
								$("#id").removeClass("is-invalid");  //이름 입력하면 경고문구 사라지게 하는것.
								$("#idMessage").html(''); 
								if($("#id").val().length >= 4 && $("#id").val().length <= 12){ 
									$.ajax({
										type : 'GET',
										url : 'check_id_ajax.jsp?id='+$("#id").val(),
										dataType : 'json',
										error : function(){
											alert('error');
										},
										success : function(json){
											//json => {"result" : "ok"}
											//json => {"result" : "fail"}
											if(json.result =="ok"){ //result가 존재한다라는 뜻
												$("#idMessage").html("<span class='text-danger'>이미 등록된 아이디 입니다.</span>");
												$("#id").addClass("is-invalid"); 
												$("#checkId").val("no");
											}else{
												$("#idMessage").html("<span class='text-success'>등록 가능한 아이디 입니다.</span>");
												$("#checkId").val("yes");
											}
										}
									});
							    }
							}); //end of id key event
							
							$("#email").on("keyup",function(){
								$("#email").removeClass("is-invalid");  //이름 입력하면 경고문구 사라지게 하는것.
								$("#emailMessage").html(''); 
								let regEmail = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
								if(regEmail.test($("#email").val())==true){ 
									$.ajax({
										type : 'GET',
										url : 'check_email_ajax.jsp?email='+$("#email").val(), 
										dataType : 'json',
										error : function(){
											alert('error');
										},
										success : function(json){
											//json => {"result" : "ok"}
											//json => {"result" : "fail"}
											if(json.result =="ok"){ //result가 존재한다라는 뜻
												$("#emailMessage").html("<span class='text-danger'>이미 등록된 이메일 입니다.</span>");
												$("#email").addClass("is-invalid"); 
												$("#checkEmail").val("no");
											}else{
												$("#emailMessage").html("<span class='text-success'>등록 가능한 이메일 입니다.</span>");
												$("#checkEmail").val("yes");
											}
										}
									});
							    }
							});//end of email
							
							$("#pwd").on("keyup",function(){
								$("#pwd").removeClass("is-invalid");  //이름 입력하면 경고문구 사라지게 하는것.
								$("#pwdMessage").html('');            
							});  //end of pwd
							
							$("#repwd").on("keyup",function(){
								$("#repwd").removeClass("is-invalid");  //이름 입력하면 경고문구 사라지게 하는것.
								$("#repwdMessage").html('');   
								if($("#repwd").val()== $("#pwd").val()){
									$("#repwdMessage").html("<span class='text-success'>비밀번호가 같으므로 사용가능합니다.</span>");
									$("#repwd").addClass("is-invalid"); 
								}
							});  //end of repwd
							
							$("#phone").on("keyup",function(){
								$("#phone").removeClass("is-invalid");  //이름 입력하면 경고문구 사라지게 하는것.
								$("#phoneMessage").html(''); 
								if($("#phone").val().length == 11){
									$("#phoneMessage").html("<span class='text-success'> 사용가능한 휴대폰 번호입니다. </span>");
									$("#phone").addClass("is-valid"); 
								}
							});//end of phone
					
						});  //end of load event
					</script>
			</div>
		</div>

	</div>
</div>
<%@ include file="../inc/footer.jsp"%>
