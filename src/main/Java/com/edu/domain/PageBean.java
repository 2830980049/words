package com.edu.domain;

import java.util.List;

public class PageBean {
    private Integer page; //当前页
    private Integer allpages; //总页数
    private Integer allnums; //总记录数
    private Integer limit; //限制数
    private List<Message> list;

    public PageBean() {
    }

    public PageBean(Integer page, Integer allpages, Integer allnums, Integer limit, List<Message> list) {
        this.page = page;
        this.allpages = allpages;
        this.allnums = allnums;
        this.limit = limit;
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "page=" + page +
                ", allpages=" + allpages +
                ", allnums=" + allnums +
                ", limit=" + limit +
                ", list=" + list +
                '}';
    }

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getAllpages() {
        return allpages;
    }

    public void setAllpages(Integer allpages) {
        this.allpages = allpages;
    }

    public Integer getAllnums() {
        return allnums;
    }

    public void setAllnums(Integer allnums) {
        this.allnums = allnums;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
