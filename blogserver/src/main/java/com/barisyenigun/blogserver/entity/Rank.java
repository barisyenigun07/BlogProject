package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rank")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "post_like_id_seq")
    @SequenceGenerator(name = "post_like_id_seq",sequenceName = "post_like_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "rank_level")
    private double rankLevel;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;
}
