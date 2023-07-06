package com.barisyenigun.blogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

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
    @Column(name = "tag_name",nullable = false,unique = true)
    private String tagName;
}
