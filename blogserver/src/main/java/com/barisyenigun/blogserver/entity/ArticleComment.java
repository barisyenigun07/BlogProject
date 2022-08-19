package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "article_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "comment_id_seq")
    @SequenceGenerator(name = "comment_id_seq",sequenceName = "comment_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "comment",nullable = false)
    private String comment;
    @ManyToOne(optional = false)
    @JoinColumn(name = "article_id")
    private Article article;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
