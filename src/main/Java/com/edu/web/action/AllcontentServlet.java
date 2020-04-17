package com.edu.web.action;

import com.edu.Service.Impl.MessageServiceImpl;
import com.edu.Service.MessageService;
import com.edu.domain.Message;
import com.edu.domain.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AllcontentServlet.do")
public class AllcontentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 查询所有留言
         * @param req
         * @param resp
         */
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
        Integer num = messageService.findCount(null);
        pageBean.setAllnums(num);
        //总页数
        if (num % limit == 0)
            pageBean.setAllpages(num / limit);
        else
            pageBean.setAllpages(num / limit + 1);
        List<Message> messageList = messageService.findAll(null,(page-1) * limit,limit);
        pageBean.setList(messageList);
        System.out.println(pageBean);
        req.setAttribute("pageBean",pageBean);
        req.getRequestDispatcher("message_list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
