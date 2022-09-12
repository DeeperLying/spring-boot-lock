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

//    @Column(name = "title", columnDefinition = "NOT NULL", length = 31, unique = true)
//    String title;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "title", referencedColumnName = "title")
    public ArticleList articleList;

    @Column(name = "date", columnDefinition = "NOT NULL")
    String date;

    @Column(name = "author", columnDefinition = "NOT NULL", length = 101)
    String author;

    @Column(name = "text", columnDefinition = "varchar(300) NOT NULL")
    String text;

    @Column(name = "textleng", columnDefinition = "varchar(3000) NOT NULL")
    String textleng;

    @Column(name = "textleng1", columnDefinition = "varchar(1000) NOT NULL")
    String textleng1;

    public String getTitle() {
        return articleList.getTitle();
    }

    public void setTitle(String title) {
        articleList.setTitle(title);
    }
}
