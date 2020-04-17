<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>留言板</title>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/index.css">
        <link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
        <script type="text/javascript" src="${ pageContext.request.contextPath }/js/js1.js"></script>
        <script type="text/javascript">
            
        </script>
    </head>

    <body>
        <header>
            <div class="container">
                <% if (null != request.getSession().getAttribute("user")) {%>
                    <nav>
                        <a href="${pageContext.request.contextPath}/UserServlet.do?method=words">我的留言</a>
                    </nav>
                    <nav>
                        <a href="${pageContext.request.contextPath}/UserServlet.do?method=finduser">我的信息</a>
                    </nav>
                <%} else { %>
                    <nav>
                        <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
                        <a href="${pageContext.request.contextPath}/reg.jsp">注册</a>
                    </nav>
                <% } %>
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>慕课网留言板</h1>
                    <p>慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。 </p>
                </div>
            </div>
        </section>
        <section class="main">
            <div class="container">
                <c:forEach var="c" items="${pageBean.list}">
                    <div class="alt-item">
                        <div class="alt-head">
                            <div class="alt-info">
                                <span>作者：<a href="">${c.username}</a></span>
                                <span>时间：${c.create_time}</span>
                            </div>
                        </div>
                        <div class="alt-content">
                            <h3>${c.title}</h3>
                            <p>${c.content}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
        <section class="page">
            <div class="page-nav">
            <div class="container">
               <ul>
                   <c:if test="${pageBean.page != 1}">
                       <li><a href="${pageContext.request.contextPath}/AllcontentServlet.do?page=1">首页</a> </li>
                       <li><a href="${pageContext.request.contextPath}/AllcontentServlet.do?${pageBean.page - 1}">上一页</a> </li>
                   </c:if>

                    <c:forEach var="i" begin="1" end="${pageBean.allpages}">
                        <c:if test="${pageBean.page == i}">
                            <li><span class="first-page">${i}</span></li>
                        </c:if>
                        <c:if test="${pageBean.page != i}">
                            <li><a href="${pageContext.request.contextPath}/AllcontentServlet.do?page=${i}">${i}</a></li>
                        </c:if>
                    </c:forEach>

                   <c:if test="${pageBean.page != pageBean.allpages}">
                       <li><a href="${pageContext.request.contextPath}/AllcontentServlet.do?page=${pageBean.page + 1}">下一页</a></li>
                       <li><a href="${pageContext.request.contextPath}/AllcontentServlet.do?page=${pageBean.allpages}">尾页</a></li>
                   </c:if>
               </ul>

           <!-- 分页内容参考视频中老师源码 -->
            </div>
            </div>
        </section>
        <footer>
            copy@慕课网
        </footer>
    </body>
</html>