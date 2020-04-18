<%--
  Created by IntelliJ IDEA.
  User: susu
  Date: 2020/4/18
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
    <title>404错误页面</title>
    <link href="${pageContext.request.contextPath}/css/pintuer.css" rel="stylesheet">
</head>

<body>
<div class="container" style=" margin-top:8%;">
    <div class="panel margin-big-top">
        <div class="text-center">
            <br>
            <h2 class="padding-top"> <stong>500错误！哦豁页面不存在</stong> </h2>
            <div class="">
                <div class="float-left">
                    <img src="${pageContext.request.contextPath}/img/404-1.gif">
                    <div class="alert"> 卧槽！页面不见了！</div>
                </div>
                <div class="float-right">
                    <img src="${pageContext.request.contextPath}/img/404-2.gif" width="260">
                </div>
            </div>
            <div class="padding-big">
                &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/AllcontentServlet.do" class="button bg-yellow">返回首页</a>
                <a href="${pageContext.request.contextPath}/max_admin/max_admin.jsp" class="button">向管理员仍板砖</a>
            </div>
        </div>
    </div>
</div>
</body></html>

