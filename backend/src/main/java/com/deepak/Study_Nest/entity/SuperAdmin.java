package com.deepak.Study_Nest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "super_admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuperAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
}
