package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article extends Post{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "article_id_seq")
    @SequenceGenerator(name = "article_id_seq",sequenceName = "article_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "title",nullable = false)
    private String title;
    @Lob
    @Column(name = "content",nullable = false,columnDefinition = "CLOB")
    private String content;
    @ManyToOne(optional = false)
    @JoinColumn(name = "userid")
    private User user;

}
