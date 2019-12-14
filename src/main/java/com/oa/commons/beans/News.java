package com.oa.commons.beans;

import java.util.Date;

public class News {
    private Integer id;
    private Integer uid;
    private NewsLabel newsLabel;
    private String title;
    private String content;
    private Date time;

    public NewsLabel getNewsLabel() {
        return newsLabel;
    }

    public void setNewsLabel(NewsLabel newsLabel) {
        this.newsLabel = newsLabel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "News{" +
                "uid=" + uid +
                ", labelId=" + newsLabel +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
