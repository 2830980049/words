<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>注册</title>
        <link rel="stylesheet" href="css/reg.css">

        <script type="text/javascript">
            function changeImg() {
                var img = document.getElementById("img");
                img.src = "${pageContext.request.contextPath}/Verification_CodeServlet.do?time=" + new Date().getTime();
            }

            
        </script>
    </head>
    <body>
    <div class="reg">
        <div class="header">
            <h1>
                <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
                <a href="${pageContext.request.contextPath}/reg.jsp">注册</a>
            </h1>
            <button></button>
        </div>
        <form action="${pageContext.request.contextPath}/UserServlet.do?method=regit" method="post">
            <div class="name">
                <input type="text" id="name" name="username" placeholder="请输入用户名">
                <p></p>
            </div>
            <div class="pwd">
                <input type="password" id="pwd1" name="password" placeholder="6-16位密码，区分大小写，不能用空格">
                <p></p>
            </div>
            <div class="confirm-pwd">
                <input type="password" id="pwd2" placeholder="确认密码">
                <p></p>
            </div>
            <div class="name">
                <input type="text" id="real_name" name="real_name" placeholder="请输入真实姓名">
                <p></p>
            </div>
            <div class="name">
                <input type="date" id="birthday" name="birthday" placeholder="请选择出生日期">
                <p></p>
            </div>
            <div class="name">
                <input type="number" id="phone" name="phone" placeholder="请输入电话">
                <p></p>
            </div>
            <div class="name">
                <input type="text" id="address" name="address" placeholder="请输入地址">
                <p></p>
            </div>
            <div class="idcode">
                <input type="text" id="verificationCode" placeholder="请输入验证码" name="verificationCode">
                <a href='#' onclick="javascript:changeImg()">&nbsp;&nbsp;&nbsp;&nbsp;换一张</a>
                <span><img id="img" src="${pageContext.request.contextPath}/Verification_CodeServlet.do"/></span>
                <div class="clear"></div>
            </div>
            <div class="btn-red">
                <input  type="submit" value="注册" id="reg-btn">
            </div>
        </form>
    </div>
    </body>
</html>
