package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.joda.time.DateTime;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @Column(name = "author", columnDefinition = "VARCHAR(101) NOT NULL", length = 101)
    String author;

    String text;
    String text_html;
    int userId;
    String create_time;
    String introduction;
    String banner;
}
