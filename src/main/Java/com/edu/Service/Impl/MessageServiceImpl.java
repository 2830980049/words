package com.edu.Service.Impl;

import com.edu.Dao.Impl.MessageDaoImpl;
import com.edu.Dao.MessageDao;
import com.edu.Service.MessageService;
import com.edu.domain.Message;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    @Override
    public List<Message> findAll(Object o) {
        MessageDao messageDao = new MessageDaoImpl();
        return messageDao.findAll(o);
    }

    @Override
    public List<Message> findAll(Object o1,Object o, int i) {
        MessageDao messageDao = new MessageDaoImpl();
        return messageDao.findAll(o1,o,i);
    }

    @Override
    public Integer findCount(Object o) {
        MessageDao messageDao = new MessageDaoImpl();
        return messageDao.findCount(o);
    }
}
