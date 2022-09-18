package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "articleList")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class ArticleList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(11) NOT NULL")
    int id;

    @Column(name = "title", columnDefinition = "VARCHAR(188) NOT NULL")
    String title;

    @Column(name = "date", nullable = false, unique = true, length=30)
    String date;

    @Column(name = "author", length = 30, nullable = false)
    String author;

    @Column(name = "text", length = 30, nullable = false)
    String text;
}
