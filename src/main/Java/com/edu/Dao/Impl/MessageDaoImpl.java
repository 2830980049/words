package com.edu.Dao.Impl;

import com.edu.Dao.MessageDao;
import com.edu.domain.Message;
import com.edu.utils.JDBC_Untils;

import java.sql.*;
import java.text.SimpleDateFormat;
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
                sql = "select * from message where username = ? order by create_time desc ";
            pres = con.prepareStatement(sql);
            if (o != null)
                pres.setObject(1,o);
            rs = pres.executeQuery();
            while (rs.next()){
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setUsername(rs.getString("username"));
                message.setContent(rs.getString("content"));
                Timestamp timestamp = rs.getTimestamp("create_time");
                java.util.Date date;
                date = new java.util.Date(timestamp.getTime());
                //时间格式化
                /*
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String datetime = sdf.format(date);
                System.out.println(datetime);
                */

                message.setCreate_time(date);
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
                sql = "select * from message order by create_time desc limit ?,?";
                pres = con.prepareStatement(sql);
                pres.setInt(1, begin);
                pres.setInt(2,i);
            }
            else{
                sql = "select * from message where username = ? order by create_time desc limit ?,?";
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
                message.setTitle(rs.getString("title"));
                message.setUser_id(rs.getInt("user_id"));
                Timestamp timestamp = rs.getTimestamp("create_time");
                java.util.Date date;
                date = new java.util.Date(timestamp.getTime());
                //时间格式化
                /*
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String datetime = sdf.format(rs.getDate("create_time"));
                 */
                message.setCreate_time(date);
                messageList.add(message);
                System.out.println(message);
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

    @Override
    public boolean create_words(Message message) {
        Connection con = null;
        PreparedStatement pres = null;
        java.util.Date date = new java.util.Date();
        try{
            Timestamp t= new Timestamp(date.getTime());
            System.out.println(t);
            con = JDBC_Untils.getConnection();
            String sql = "insert into message values(null,?,?,?,?,?)";
            pres = con.prepareStatement(sql);
            pres.setInt(1,message.getUser_id());
            pres.setString(2,message.getUsername());
            pres.setString(3,message.getTitle());
            pres.setString(4, message.getContent());
            pres.setString(5, String.valueOf(t));
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
    public boolean edit_words(Message message) {
        Connection con = null;
        PreparedStatement pres = null;
        try{
            con = JDBC_Untils.getConnection();
            String sql = "update message set title = ?, content = ? where id = ?";
            pres = con.prepareStatement(sql);
            pres.setString(1,message.getTitle());
            pres.setString(2,message.getContent());
            pres.setInt(3, message.getId());
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
    public boolean delete_words(int id) {
        Connection con = null;
        PreparedStatement pres = null;
        try{
            con = JDBC_Untils.getConnection();
            String sql = "delete from message where id = ?";
            pres = con.prepareStatement(sql);
            pres.setInt(1,id);
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
}
