package com.edu.web.action;

import com.edu.Service.Impl.MessageServiceImpl;
import com.edu.Service.MessageService;
import com.edu.domain.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MessageServlet.do")
public class MessageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methNmae = req.getParameter("method");
        if ("findAlluser".equals(methNmae))
            findAlluser(req,resp);
    }

    /**
     * 查询所有留言
     * @param req
     * @param resp
     */
    private void findAlluser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageService messageService = new MessageServiceImpl();
        List<Message> messageList = messageService.findAll(null);
        req.setAttribute("messageList",messageList);
        req.getRequestDispatcher("message_list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
