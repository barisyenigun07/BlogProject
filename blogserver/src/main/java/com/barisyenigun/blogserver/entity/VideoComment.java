package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "video_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "video_comment_id_seq")
    @SequenceGenerator(name = "video_comment_seq_id",sequenceName = "video_comment_id_seq",allocationSize = 1)
    @Column(name = "video_comment_id")
    private Long id;
    @Column(name = "comment")
    private String comment;
    @ManyToOne(optional = false)
    @JoinColumn(name = "video_id")
    private Video video;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
