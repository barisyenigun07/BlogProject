package com.barisyenigun.blogserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "posts_id_seq")
    @SequenceGenerator(name = "posts_id_seq",sequenceName = "posts_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "title")
    private String title;
    @NotNull
    @Column(name = "description")
    private String description;
    @Column(name = "caption_photo_link")
    private String captionPhotoLink;
    @NotNull
    @Column(name = "content", columnDefinition = "text")
    private String content;
    @NotNull
    @Column(name = "post_type")
    private String postType;
    @Column(name = "published_date")
    private ZonedDateTime publishedDate = ZonedDateTime.now(ZoneId.systemDefault());
    @Column(name = "modified_date")
    private ZonedDateTime modifiedDate;
    @ManyToMany
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Comment> comments;
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Rate> rates;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
