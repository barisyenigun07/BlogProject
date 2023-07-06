package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "post")
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
    @Column(name = "caption_photo_link")
    private String captionPhotoLink;
    @Lob
    @Column(name = "content",nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(name = "post_type",nullable = false)
    private String postType;
    @Column(name = "published_time")
    private LocalDate publishedDate = LocalDate.now();
    @Column(name = "modified_time")
    private LocalDate modifiedDate;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
    @ManyToOne(cascade = REMOVE)
    @JoinColumn(name = "user_id")
    private User user;
}
