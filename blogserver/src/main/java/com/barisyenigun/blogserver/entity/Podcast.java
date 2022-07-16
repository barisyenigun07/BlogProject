package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "podcast")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Podcast {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "podcast_id_seq")
    @SequenceGenerator(name = "podcast_id_seq",sequenceName = "podcast_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Lob
    @Column(name = "content")
    private Byte[] content;
    @ManyToOne(optional = false)
    @JoinColumn(name = "userid")
    private User user;

}
