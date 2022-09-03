package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "article")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Article {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    String title;
    String date;
    String author;
    String text;
    String textleng;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextleng() {
        return textleng;
    }

    public void setTextleng(String textleng) {
        this.textleng = textleng;
    }
}
