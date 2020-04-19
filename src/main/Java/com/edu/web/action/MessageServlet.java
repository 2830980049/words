package com.edu.web.action;

import com.edu.Service.Impl.MessageServiceImpl;
import com.edu.Service.MessageService;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/MessageServlet.do")
public class MessageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methNmae = req.getParameter("method");
        if ("words".equals(methNmae))
            words(req,resp);
            else if ("create_words".equals(methNmae)) {
                try {
                    create_words(req,resp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
                else if ("edit_ui".equals(methNmae))
                    edit_ui(req,resp);
                    else if("edit_words".equals(methNmae))
                        edit_words(req,resp);
                        else if("delete_words".equals(methNmae))
                            delete_words(req,resp);
    }


    /**
     * 查询所有留言
     * @param req
     * @param resp
     */
    private void words(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageService messageService = new MessageServiceImpl();
        User user = new User();
        user = (User)req.getSession().getAttribute("user");
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
        //System.out.println(pageBean);
        req.setAttribute("pageBean",pageBean);
        req.setAttribute("pageBean",pageBean);
        req.getRequestDispatcher("/message/my_message_list.jsp").forward(req,resp);
    }

    /**
     * 新建留言
     * @param req
     * @param resp
     */
    private void create_words(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        MessageService messageService = new MessageServiceImpl();
        Message message = new Message();
        User user = (User)req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        System.out.println(user);
        message.setUser_id(user.getId());
        message.setTitle(title);
        message.setContent(content);
        message.setUsername(user.getUsername());
        boolean flag = messageService.create_words(message);
        if (flag)
            resp.sendRedirect(req.getContextPath()+"/AllcontentServlet.do");
        else
            resp.sendRedirect(req.getContextPath()+"/message/add_message.jsp");
    }

    /**
     * edit页面
     */

    private void edit_ui(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Message message = new Message();
        message.setId(Integer.parseInt(req.getParameter("id")));
        message.setUser_id(Integer.parseInt(req.getParameter("user_id")));
        message.setTitle(req.getParameter("title"));
        message.setContent(req.getParameter("content"));
        message.setUsername(req.getParameter("username"));
        System.out.println(message);
        req.setAttribute("message",message);
        req.getRequestDispatcher("/message/edit_message.jsp").forward(req,resp);
    }

    /**
     *编辑数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void edit_words(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        MessageService messageService = new MessageServiceImpl();
        Message message = new Message();
        System.out.println(req.getParameter("id"));
        message.setId(Integer.parseInt(req.getParameter("id")));
        message.setTitle(req.getParameter("title"));
        message.setContent(req.getParameter("content"));
        boolean flag = messageService.edit_words(message);
        if (flag)
            resp.sendRedirect(req.getContextPath()+"/MessageServlet.do?method=words");
        else
            resp.sendRedirect(req.getContextPath()+"/message/edit_message.jsp");
    }

    /**
     * 删除留言
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void delete_words(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        MessageService messageService = new MessageServiceImpl();
        boolean flag = messageService.delete_words(Integer.parseInt(req.getParameter("id")));
        if (flag)
            resp.sendRedirect(req.getContextPath()+"/MessageServlet.do?method=words");
        else
            resp.sendRedirect(req.getContextPath()+"/message/edit_message.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
