package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "video")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "video_id_seq")
    @SequenceGenerator(name = "video_id_seq",sequenceName = "video_id_seq")
    @Column(name = "id")
    private Long id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "contentLink",nullable = false)
    private String contentLink;
    @Column(name = "explanation")
    private String explanation;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
