package com.edu.Dao.Impl;

import com.edu.Dao.MessageDao;
import com.edu.domain.Message;
import com.edu.utils.JDBC_Untils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements MessageDao {

    @Override
    public List<Message> findAll(Object o) {
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        List<Message> messageList = new ArrayList<Message>();
        try{
            con = JDBC_Untils.getConnection();
            String sql = null;
            if (o == null)
                sql = "select * from message";
            else
                sql = "select * from message where username = ?";
            pres = con.prepareStatement(sql);
            if (o != null)
                pres.setObject(1,o);
            rs = pres.executeQuery();
            while (rs.next()){
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setUsername(rs.getString("username"));
                message.setContent(rs.getString("content"));
                message.setCreate_time(rs.getDate("create_time"));
                message.setTitle(rs.getString("title"));
                message.setUser_id(rs.getInt("user_id"));
                messageList.add(message);
            }
            return messageList;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_Untils.relese(pres, con, rs);
        }
        return null;
    }

    @Override
    public List<Message> findAll(Object o1,Object o, int i) {
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        List<Message> messageList = new ArrayList<Message>();
        try{
            con = JDBC_Untils.getConnection();
            String sql = null;
            int begin = (int) o;
            if (o1 == null){
                sql = "select * from message limit ?,?";
                pres = con.prepareStatement(sql);
                pres.setInt(1, begin);
                pres.setInt(2,i);
            }
            else{
                sql = "select * from message where username = ? limit ?,?";
                pres = con.prepareStatement(sql);
                pres.setString(1,(String)o1);
                pres.setInt(2, begin);
                pres.setInt(3,i);

            }
            rs = pres.executeQuery();
            while (rs.next()){
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setUsername(rs.getString("username"));
                message.setContent(rs.getString("content"));
                message.setCreate_time(rs.getDate("create_time"));
                message.setTitle(rs.getString("title"));
                message.setUser_id(rs.getInt("user_id"));
                messageList.add(message);
            }
            return messageList;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_Untils.relese(pres, con, rs);
        }
        return null;
    }

    @Override
    public Integer findCount(Object o) {
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        Integer num = 0;
        try{
            String sql = null;
            con = JDBC_Untils.getConnection();
            if (o == null){
                sql = "select count(*) as count from message";
                pres = con.prepareStatement(sql);

            }
            else{
                sql = "select count(*) as count from message where username = ?";
                pres = con.prepareStatement(sql);
                pres.setString(1,(String)o);
            }
            rs = pres.executeQuery();
            if (rs.next()){
                num = rs.getInt("count");
                return num;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_Untils.relese(pres, con, rs);
        }
        return 0;
    }
}
