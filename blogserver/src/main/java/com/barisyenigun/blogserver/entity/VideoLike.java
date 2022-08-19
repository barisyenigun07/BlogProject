package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "video_like")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoLike {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "video_like_id_seq")
    @SequenceGenerator(name = "video_like_id_seq",sequenceName = "video_like_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "liked")
    private boolean liked;
    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
