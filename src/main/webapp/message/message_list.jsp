<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>留言板</title>
        <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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
                        <a href="${pageContext.request.contextPath}/MessageServlet.do?method=create_words">新建留言</a>
                    </nav>
                    <nav>
                        <a href="${pageContext.request.contextPath}/MessageServlet.do?method=words">我的留言</a>
                    </nav>
                    <nav>
                        <a href="${pageContext.request.contextPath}/UserServlet.do?method=finduser">我的信息</a>
                    </nav>
                    <nav>
                        <a href="${pageContext.request.contextPath}/UserServlet.do?method=out">登出</a>
                    </nav>
                    <nav>
                        <a href="${pageContext.request.contextPath}/admin/edit_user.jsp">修改密码</a>
                    </nav>
                <%} else { %>
                    <nav>
                        <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
                        <a href="${pageContext.request.contextPath}/admin/reg.jsp">注册</a>
                    </nav>
                <% } %>
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>留言板</h1>
                    <p>你可以把你的美好回忆留在这里与其他人分享</p>
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
                                <span>时间：<fmt:formatDate type="both" value="${c.create_time}"  pattern="yyyy-MM-dd HH:mm"/></span>
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
            <div class="container" style="text-align: center">
                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-lg">
                        <li><a href="${pageContext.request.contextPath}/AllcontentServlet.do?page=1">首页</a> </li>
                        <c:if test="${pageBean.page != 1}">
                            <li><a href="${pageContext.request.contextPath}/AllcontentServlet.do?page=${pageBean.page - 1}" aria-label="Next"><span aria-hidden="true">&laquo;</span></a></li>
                        </c:if>

                        <c:if test="${pageBean.page == 1}">
                            <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${pageBean.allpages}">
                            <c:if test="${pageBean.page == i}">
                                <li class="active"><a href="#">${i}<span class="sr-only">(current)</span></a></li>
                            </c:if>
                            <c:if test="${pageBean.page != i}">
                                <li><a href="${pageContext.request.contextPath}/AllcontentServlet.do?page=${i}">${i}</a></li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${pageBean.page != pageBean.allpages}">
                            <li><a href="${pageContext.request.contextPath}/AllcontentServlet.do?page=${pageBean.page + 1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                        </c:if>

                        <c:if test="${pageBean.page == pageBean.allpages}">
                            <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                        </c:if>

                        <li><a href="${pageContext.request.contextPath}/AllcontentServlet.do?page=${pageBean.allpages}">尾页</a></li>
                    </ul>
                </nav>
            </div>

        </section>
        <footer>
            Create By WuQiLi
        </footer>
    </body>
</html>