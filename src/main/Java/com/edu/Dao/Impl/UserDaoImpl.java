package com.edu.Dao.Impl;

import com.edu.Dao.UserDao;
import com.edu.domain.User;
import com.edu.utils.JDBC_Untils;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    @Override
    public User Login(User user) {
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        try{
            con = JDBC_Untils.getConnection();
            String sql = "select * from user where username = ? and password = ?";
            pres = con.prepareStatement(sql);
            pres.setString(1,user.getUsername());
            pres.setString(2,user.getPassword());
            rs = pres.executeQuery();
            if (rs.next()){
                User user1 = new User();
                user1.setId(rs.getInt("id"));
                user1.setUsername(rs.getString("username"));
                user1.setPassword(rs.getString("password"));
                user1.setReal_name(rs.getString("real_name"));
                user1.setBirthday(rs.getDate("birthday"));
                user1.setPhone(rs.getString("phone"));
                user1.setAddress(rs.getString("address"));
                return user1;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_Untils.relese(pres,con,rs);
        }
        return null;
    }

    @Override
    public boolean Regit(User user) {
        Connection con = null;
        PreparedStatement pres = null;
        try{
            con = JDBC_Untils.getConnection();
            String sql = "insert into user values(null,?,?,?,?,?,?)";
            pres = con.prepareStatement(sql);
            pres.setString(1,user.getUsername());
            pres.setString(2,user.getPassword());
            pres.setString(3,user.getReal_name());
            pres.setDate(4, (Date) user.getBirthday());
            pres.setString(5,user.getPhone());
            pres.setString(6,user.getAddress());
            int num =  pres.executeUpdate();
            if (num > 0)
                return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_Untils.relese(pres,con);
        }
        return false;
    }

    @Override
    public boolean Update_date(User user) {
        Connection con = null;
        PreparedStatement pres = null;
        try{
            con = JDBC_Untils.getConnection();
            String sql = "update user set password = ?, real_name = ?, birthday = ?, phone = ?, address = ? where username = ?";
            pres = con.prepareStatement(sql);
            pres.setString(1,user.getPassword());
            pres.setString(2,user.getReal_name());
            pres.setDate(3, (Date) user.getBirthday());
            pres.setString(4,user.getPhone());
            pres.setString(5,user.getAddress());
            pres.setString(6,user.getUsername());
            int num =  pres.executeUpdate();
            if (num > 0)
                return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_Untils.relese(pres,con);
        }
        return false;
    }

    @Override
    public User finduser(String username) {
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        try{
            con = JDBC_Untils.getConnection();
            System.out.println(username);
            String sql = "select * from user where username = ?";
            pres = con.prepareStatement(sql);
            pres.setString(1,username);
            rs = pres.executeQuery();
            if (rs.next()){
                User user = new User();
                user.setUsername(username);
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setBirthday(rs.getDate("birthday"));
                user.setReal_name(rs.getString("real_name"));
                return user;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_Untils.relese(pres,con,rs);
        }
        return null;
    }
}
