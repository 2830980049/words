package com.edu.Dao;

import com.edu.domain.User;

public interface UserDao {
    boolean Login(User user);

    boolean Regit(User user);

    boolean Update_date(User user);

    User finduser(String username);
}
