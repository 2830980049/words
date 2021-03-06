package com.edu.Service;

import com.edu.domain.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll(Object o);

    List<Message> findAll(Object o1,Object o, int i);

    Integer findCount(Object o);

    boolean create_words(Message message);

    boolean edit_words(Message message);

    boolean delete_words(int id);
}
