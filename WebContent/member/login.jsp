<%@ page pageEncoding="UTF-8"%>
<%@ include file = "../inc/header.jsp" %>

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
          <%if(memberDto == null){ %>
            <h5 class="card-title">로그인</h5>
            <form name="f" method="post" action="check_login.jsp">
              <div class="form-group"> 
                <input type="email" id="eamil" name="email" class="form-control" placeholder="Your Email *" value="" />
              </div>
              <div class="form-group">
                <input type="password" id="pwd" name="pwd" class="form-control" placeholder="Your Password *" value="" />
              </div>
              <div class="form-group">
                <input type="submit" id="checkLogin" class="btn btn-primary" value="Login" />
              </div>
              <div class="form-group">
                <a href="" id="forgetPwd" class="ForgetPwd" >Forget Password?</a>
              </div>
             
            </form>  
            <script>
            	$(function(){
            		$("#checkLogin").on("click",function(event){
            			event.preventDefault();
            			if($("#email").val()==""){
            				alert('이메일을 입력하세요.');
            				$("#email").focus();
            			}
            			if($("#pwd").val()==""){
            				alert('비밀번호를 입력하세요.');
            				$("#pwd").focus();
            				return;
            			}
            			f.submit();
            		});
            	});
            </script>
            <%}else { %>
           		<h5 class="card-title"> 로그인을 하셨습니다.</h5>
            <%} %>
            
          </div>
          
       
        </div>
      </div>
    </div>
  </div>
  	<%@ include file = "../inc/footer.jsp" %>
 
 <%--빨간경고창뜨게끔--%>