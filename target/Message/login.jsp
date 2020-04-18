<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>登录</title>
		<link rel="stylesheet" href="css/login.css">

		<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

		<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

		<script type="text/javascript">
            function changeImg() {
                var img = document.getElementById("img");
                img.src = "${pageContext.request.contextPath}/Verification_CodeServlet.do?time=" + new Date().getTime();
            }
   
		</script>
	</head>
	<body>
		<div class="login">
			<div class="header">
				<h1>
					<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
					<a href="${pageContext.request.contextPath}/admin/reg.jsp">注册</a>
				</h1>
				<button></button>
			</div>
			<form action="${pageContext.request.contextPath}/UserServlet.do?method=login" method="post">
				<div class="name">
					<p style="color: #a94442">${msg}</p>
				</div>
				<div class="name">
					<input type="text" id="name" name="username" placeholder="请输入登录用户名">
					<p></p>
				</div>
				<div class="pwd">
					<input type="password" minlength="6" maxlength="16" id="pwd" name="password" placeholder="6-16位密码，区分大小写，不能用空格">
					<p></p>
				</div>
				<div class="idcode">
					<input type="text" id="verificationCode" placeholder="请输入验证码" name="verificationCode">
					<a href='#' onclick="javascript:changeImg()">&nbsp;&nbsp;&nbsp;&nbsp;换一张</a>
					<span><img id="img" src="${pageContext.request.contextPath}/Verification_CodeServlet.do"/></span>
					<div class="clear"></div>
				</div>
				<div class="autoLogin">
					<label for="">
						<input type="checkbox" checked="checked">
						下次自动登录
					</label>
					<a href="">忘记密码</a>
				</div>
				<div class="btn-red">
					<input  type="submit" value="登录" id="login-btn">
				</div>
			</form>
		</div>
	</body>
</html>