package com.evan.wj.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author SuperLee
 * @date 2023/8/5 下午2:39
 */
@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    int user_id;
    int article_id;
    int parent_comment_id;
    String nickname;
    String avatar;
    String content;
    int like;
    int Level;
}
