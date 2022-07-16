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
public class Video extends Post{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "video_id_seq")
    @SequenceGenerator(name = "video_id_seq",sequenceName = "video_id_seq")
    @Column(name = "id")
    private Long id;
    @Column(name = "title",nullable = false)
    private String title;
    @Lob
    @Column(name = "content",nullable = false,columnDefinition = "BLOB")
    private Byte[] content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
