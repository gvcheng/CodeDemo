package com.gvc.pojo;

import java.io.Serializable;

public class Article implements Serializable {
    // 序列化版本号（必须添加，避免类结构变化导致反序列化失败）
    private static final long serialVersionUID = 1L;

    /**
    * 文章id
    */
    private Integer id;

    /**
    * 文章标题
    */
    private String title;

    /**
    * 文章内容
    */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}