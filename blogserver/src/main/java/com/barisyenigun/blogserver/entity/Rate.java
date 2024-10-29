package com.barisyenigun.blogserver.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "rate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "post_rate_id_seq")
    @SequenceGenerator(name = "post_rate_id_seq",sequenceName = "post_rate_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "rate_level")
    private double rateLevel;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
