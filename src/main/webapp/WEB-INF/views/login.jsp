<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/images/logo.png" type="image/x-icon" />
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/images/logo.png"
			type="image/x-icon" />
       
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/lib/bootstrap/css/bootstrap-image-gallery.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/lib/bootstrap/css/ie10mobile.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/lib/font-awesome/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/lib/custom/css/site.css" />
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}

</style>
</head>
<body onload='document.f.j_username.focus();'>

	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful.<br /> REASON :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

<div id="loginModal" class="modal show" tabindex="-1" >
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
      	<i><img class="logo" src="resources/images/logo.png" /></i>
          <h1 class="text-center title-header">Jian Rin Order Managment</h1>
      </div>
      <div class="modal-body">
          <form name="loginForm" class="form col-md-12 center-block" action="<c:url value='j_spring_security_check'/>" method='POST'>
            <div class="form-group">
              <input type="text"  name='username' value='' class="form-control input-lg" placeholder="Email">
            </div>
            <div class="form-group">
              <input type="password" name='password' class="form-control input-lg" placeholder="Password">
            </div>
            <div class="form-group">
              <button class="btn btn-block btn-lg btn-success">Sign In</button>
            </div>
          </form>
      </div>

  </div>
  </div>
</div>
<script src="lib/jquery/jquery-1.9.1.js"></script>
</body>
</html>