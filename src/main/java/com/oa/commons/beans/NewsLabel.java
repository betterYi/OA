package com.oa.commons.beans;

import java.util.List;

public class NewsLabel {
    private int id;
    private String name;
    private String content;

    private NewsLabel parent;
    private List<NewsLabel> child;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewsLabel getParent() {
        return parent;
    }

    public void setParent(NewsLabel parent) {
        this.parent = parent;
    }

    public List<NewsLabel> getChild() {
        return child;
    }

    @Override
    public String toString() {
        return "NewsLabel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", parent=" + parent +
                ", child=" + child +
                '}';
    }

    public void setChild(List<NewsLabel> child) {
        this.child = child;
    }
}
