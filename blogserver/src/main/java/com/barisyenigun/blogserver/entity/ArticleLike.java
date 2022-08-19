package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "article_like")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleLike {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "article_like_id_seq")
    @SequenceGenerator(name = "article_like_id_seq",sequenceName = "article_like_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "liked")
    private boolean liked;
    @ManyToOne(optional = false)
    @JoinColumn(name = "article_id")
    private Article article;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
