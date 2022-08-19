package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "podcast_like")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PodcastLike {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "podcast_like_id_seq")
    @SequenceGenerator(name = "podcast_like_id_seq",sequenceName = "podcast_like_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "liked")
    private boolean liked;
    @ManyToOne(optional = false)
    @JoinColumn(name = "podcast_id")
    private Podcast podcast;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
