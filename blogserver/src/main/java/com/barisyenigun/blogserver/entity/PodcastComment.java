package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "podcast_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PodcastComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "podcast_comment_seq_id")
    @SequenceGenerator(name = "podcast_comment_seq_id",sequenceName = "podcast_comment_seq_id")
    @Column(name = "id")
    private Long id;
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "podcast_id")
    private Podcast podcast;
}
