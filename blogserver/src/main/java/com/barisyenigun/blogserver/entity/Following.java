package com.barisyenigun.blogserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "following")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Following {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "follower_user")
    private User follower;
    @ManyToOne
    @JoinColumn(name = "followed_user")
    private User followed;
}
