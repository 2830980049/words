package com.edu.Service.Impl;

import com.edu.Dao.Impl.UserDaoImpl;
import com.edu.Dao.UserDao;
import com.edu.Service.UserService;
import com.edu.domain.User;

public class UserServiceImpl implements UserService {
    @Override
    public User Login(User user) {
        UserDao userDao = new UserDaoImpl();
        return userDao.Login(user);
    }

    @Override
    public boolean Regit(User user) {
        UserDao userDao = new UserDaoImpl();
        return userDao.Regit(user);
    }

    @Override
    public boolean Update_date(User user) {
        UserDao userDao = new UserDaoImpl();
        return userDao.Update_date(user);
    }

    @Override
    public User finduser(String username) {
        UserDao userDao = new UserDaoImpl();
        return userDao.finduser(username);
    }
}
