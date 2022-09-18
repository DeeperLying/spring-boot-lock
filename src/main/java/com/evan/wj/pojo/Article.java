package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "article")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(11) NOT NULL")
    int id;

    @Column(name = "title", columnDefinition = "VARCHAR(30) NOT NULL UNIQUE")
    String title;

//    @OneToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "title", referencedColumnName = "title")
//    public ArticleList articleList;

    @Column(name = "date", columnDefinition = "VARCHAR(22) NOT NULL")
    String date;

    @Column(name = "author", columnDefinition = "VARCHAR(101) NOT NULL", length = 101)
    String author;

    @Column(name = "text", columnDefinition = "varchar(300) NOT NULL")
    String text;

    @Column(name = "textleng", columnDefinition = "VARCHAR(100) NOT NULL")
    String textleng;

//    public String getTitle() {
//        return articleList.getTitle();
//    }
//
//    public void setTitle(String title) {
//        articleList.setTitle(title);
//    }
}
