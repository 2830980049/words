<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>我的信息</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="../css/add.css">
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}//AllcontentServlet.do">
                        返回留言板
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="jumbotron">
                <h1>Hello, ${user1.username}!</h1>
                <p>信息都在这里了 ^_^</p>
            </div>
            <div class="page-header">
                <h3><small>个人信息</small></h3>
            </div>
             <form class="form-horizontal" action="${pageContext.request.contextPath}/UserServlet.do?method=update" method="post">
               
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">用户 ：</label>
                    <div class="col-sm-6">
                        <input name="username" class="form-control" id="username" value="${user1.username}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码 ：</label>
                    <div class="col-sm-6">
                        <input name="password" class="form-control" id="password" value="${user1.password}">
                    </div>
                </div>

                 <div class="form-group">
                     <label for="name" class="col-sm-2 control-label">真实姓名 ：</label>
                     <div class="col-sm-6">
                         <input name="real_name" class="form-control" id="real_name" value="${user1.real_name}">
                     </div>
                 </div>

                 <div class="form-group">
                     <label for="name" class="col-sm-2 control-label">出生日期 ：</label>
                     <div class="col-sm-6">
                         <input type="date" name="birthday" class="form-control" id="birthday" value="${user1.birthday}">
                     </div>
                 </div>

                 <div class="form-group">
                     <label for="name" class="col-sm-2 control-label">电话 ：</label>
                     <div class="col-sm-6">
                         <input name="phone" class="form-control" id="phone" value="${user1.phone}" >
                     </div>
                 </div>

                 <div class="form-group">
                     <label for="name" class="col-sm-2 control-label">地址 ：</label>
                     <div class="col-sm-6">
                         <input name="address" class="form-control" id="address" value="${user1.address}" >
                     </div>
                 </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
        <footer class="text-center" >
            Create By WuQiLi
        </footer>
    </body>
</html>
