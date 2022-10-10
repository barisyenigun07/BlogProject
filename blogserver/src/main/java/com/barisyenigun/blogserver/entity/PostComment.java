package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "post_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "post_comment_id_seq")
    @SequenceGenerator(name = "post_comment_id_seq",sequenceName = "post_comment_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Lob
    @Column(name = "comment",nullable = false,columnDefinition = "TEXT")
    private String comment;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;
}
