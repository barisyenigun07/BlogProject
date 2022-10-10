package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "posts_id_seq")
    @SequenceGenerator(name = "posts_id_seq",sequenceName = "posts_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    @Lob
    @Column(name = "content",nullable = false,columnDefinition = "TEXT")
    private String content;
    @Column(name = "published_time")
    private LocalDate publishedTime;
    @Column(name = "modified_time")
    private LocalDate modifiedTime;
    @Column(name = "post_type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
    @ManyToOne(cascade = REMOVE)
    @JoinColumn(name = "user_id")
    private User user;
}
