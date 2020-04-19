package com.edu.web.action;

import com.edu.Service.Impl.MessageServiceImpl;
import com.edu.Service.Impl.UserServiceImpl;
import com.edu.Service.MessageService;
import com.edu.Service.UserService;
import com.edu.domain.Message;
import com.edu.domain.PageBean;
import com.edu.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/UserServlet.do")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getParameter("method");
        System.out.println(methodName);
        if ("login".equals(methodName))
            Login(req,resp);
            else if("regit".equals(methodName))
                Regit(req,resp);
                else if("finduser".equals(methodName))
                    finduser(req,resp);
                    else if("update".equals(methodName))
                        Update_date(req,resp);
                        else if ("update_pwd".equals(methodName))
                            update_pwd(req,resp);
                            else if ("out".equals(methodName))
                                out(req,resp);
    }


    /**
     * 用户登录
     * @param req
     * @param resp
     */
    private void Login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        User user = new User();
        String verificationCode = "";
        String verificationCode_1 = null;
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        verificationCode = req.getParameter("verificationCode");
        verificationCode_1 = (String)req.getSession().getAttribute("verificationCode");
        User user1 = userService.Login(user);
        if (verificationCode.equalsIgnoreCase(verificationCode_1)){
            if (user1 != null){
                req.getSession().setAttribute("user",user1);
                resp.sendRedirect(req.getContextPath()+"/AllcontentServlet.do");
            }
            else{
                req.setAttribute("msg","账号或者密码错误");
                resp.sendRedirect(req.getContextPath()+"/login.jsp");
            }
        }
        else
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }

    /**
     * 登出
     */
    private void out(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        System.out.println(req.getSession().getAttribute("user"));
        resp.sendRedirect(req.getContextPath()+"/AllcontentServlet.do");
    }

    /**
     * 用户注册
     * @param req
     * @param resp
     */
    private void Regit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setAddress(req.getParameter("address"));
        user.setBirthday(Date.valueOf(req.getParameter("birthday")));
        user.setReal_name(req.getParameter("real_name"));
        System.out.println(req.getParameter("phone"));
        user.setPhone(req.getParameter("phone"));
        System.out.println(user);
        String verificationCode = req.getParameter("verificationCode");
        String verificationCode_1 = (String)req.getSession().getAttribute("verificationCode");
        boolean flag = userService.Regit(user);
        if(verificationCode.equalsIgnoreCase(verificationCode_1)){
            if (flag)
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            else
                resp.sendRedirect("reg.jsp");
        }
        else
        resp.sendRedirect(req.getContextPath()+"/admin/reg.jsp");
    }

    /**
     * 用户信息查询
     */
    private void finduser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        User user = (User)req.getSession().getAttribute("user");
        System.out.println(user);
        User user1 = userService.finduser(user.getUsername());
        System.out.println(user1);
        req.setAttribute("user1",user1);
        req.getRequestDispatcher("admin/user.jsp").forward(req,resp);
    }

    /**
     * 用户更新
     * @param req
     * @param resp
     */
    private void Update_date(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setReal_name(req.getParameter("real_name"));
        user.setBirthday(Date.valueOf(req.getParameter("birthday")));
        user.setAddress(req.getParameter("address"));
        user.setPassword(req.getParameter("password"));
        user.setPhone(req.getParameter("phone"));
        UserService userService = new UserServiceImpl();
        boolean flag = userService.Update_date(user);
        if (flag){
            UserServlet userServlet = new UserServlet();
            userServlet.out(req,resp);
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

        else
            resp.sendRedirect(req.getContextPath()+"/admin/user.jsp");
    }

    /**
     *修改密码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void update_pwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean flag = userService.update_pwd(username,password);
        if (flag){
            UserServlet userServlet = new UserServlet();
            userServlet.out(req,resp);
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
        else
            resp.sendRedirect(req.getContextPath()+"/admin/edit_user.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
