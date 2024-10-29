package com.barisyenigun.blogserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tag_id_seq")
    @SequenceGenerator(name = "tag_id_seq",sequenceName = "tag_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "tag_name", unique = true)
    private String tagName;
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> posts;
}
