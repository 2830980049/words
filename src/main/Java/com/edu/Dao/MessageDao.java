package com.edu.Dao;

import com.edu.domain.Message;

import java.util.List;

public interface MessageDao {
    List<Message> findAll(Object o);

    List<Message> findAll(Object o1,Object o, int i);

    Integer findCount(Object o);
}
