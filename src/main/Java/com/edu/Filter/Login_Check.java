package com.edu.Filter;

import com.edu.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Login_Check implements Filter {

    public static final List<String> login_page = new ArrayList<String>();
    static {
        login_page.add("/message_list.jsp");
        login_page.add("/UserServlet.do");
        login_page.add("/AllcontentServlet.do");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse reps = (HttpServletResponse)servletResponse;
        String url = req.getRequestURI();
        String path = req.getContextPath();
        System.out.println();
        System.out.println();
        System.out.println();
        //取得首页名称
        String targetURL = url.substring(path.length());
        System.out.println("url "+url);
        System.out.println("path"+path);
        User user = (User)req.getSession().getAttribute("user");
        System.out.println("user "+user);
        boolean flag = false;
        System.out.println("targetURL "+targetURL+ " "+targetURL.equals("/"));
        if(targetURL.equals("/")){
            reps.sendRedirect(req.getContextPath()+"/AllcontentServlet.do");
            return;
        }
        for (String list:login_page){
            if(targetURL.contains(list) || targetURL.equals(list)){
                System.out.println("targetURL"+targetURL);
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }
        if (user == null){
            if(!targetURL.equals("/"))
                req.setAttribute("msg","你还没有登录，请先登录");
            req.getRequestDispatcher("login.jsp").forward(req,reps);
            return;
        }
        else{
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
    }

}
