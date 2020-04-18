package com.edu.Service;

import com.edu.domain.User;

public interface UserService {
    User Login(User user);

    boolean Regit(User user);

    boolean Update_date(User user);

    User finduser(String username);
}
