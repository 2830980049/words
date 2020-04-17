package com.edu.domain;

import java.util.Date;

public class Message {
    private Integer id;
    private Integer user_id;
    private String username;
    private String title;
    private String content;
    private Date create_time;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", create_time=" + create_time +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Message(Integer id, Integer user_id, String username, String title, String content, Date create_time) {
        this.id = id;
        this.user_id = user_id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.create_time = create_time;
    }

    public Message() {
    }
}
