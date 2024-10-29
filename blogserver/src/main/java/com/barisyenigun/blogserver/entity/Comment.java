package com.barisyenigun.blogserver.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_comment_id_seq")
    @SequenceGenerator(name = "post_comment_id_seq", sequenceName = "post_comment_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Column(name = "published_date")
    private ZonedDateTime publishedDate = ZonedDateTime.now(ZoneId.systemDefault());
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE)
    private List<Comment> replies;
}
