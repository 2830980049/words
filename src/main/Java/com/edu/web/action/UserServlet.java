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
    }


    /**
     * 用户登录
     * @param req
     * @param resp
     */
    private void Login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        User user = new User();
        boolean flag = false;
        String verificationCode = "";
        String verificationCode_1 = null;
        System.out.println(req.getSession().getAttribute("user"));
        if (req.getSession().getAttribute("user") != null){
            user = (User)req.getSession().getAttribute("user");
            flag = true;
        }
        else {
            user.setUsername(req.getParameter("username"));
            user.setPassword(req.getParameter("password"));
            verificationCode = req.getParameter("verificationCode");
            verificationCode_1 = (String)req.getSession().getAttribute("verificationCode");
        }
        boolean falg = false;
        if (verificationCode.equalsIgnoreCase(verificationCode_1) || flag){
            falg = userService.Login(user);
            if (falg){
                MessageService messageService = new MessageServiceImpl();
                PageBean pageBean = new PageBean();
                Integer page = 1;
                //当前页
                if (req.getParameter("page") != null)
                    page = Integer.parseInt(req.getParameter("page"));
                //限制数
                Integer limit = 6;
                pageBean.setLimit(limit);
                pageBean.setPage(page);
                //总记录数
                Integer num = messageService.findCount(user.getUsername());
                pageBean.setAllnums(num);
                //总页数
                if (num % limit == 0)
                    pageBean.setAllpages(num / limit);
                else
                    pageBean.setAllpages(num / limit + 1);
                List<Message> messageList = messageService.findAll(user.getUsername(),(page-1) * limit,limit);
                pageBean.setList(messageList);
                System.out.println(pageBean);
                req.setAttribute("pageBean",pageBean);
                req.getSession().setAttribute("user",user);
                req.getRequestDispatcher("my_message_list.jsp").forward(req,resp);
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
        resp.sendRedirect("reg.jsp");
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
        req.getRequestDispatcher("user.jsp").forward(req,resp);
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
        if (flag)
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        else
            resp.sendRedirect(req.getContextPath()+"/user.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
