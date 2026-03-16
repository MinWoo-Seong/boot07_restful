package com.ocj1778.boot07_restful.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String password;
    private Boolean enabled;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> comments;

    @Builder
    public Member(String name, String email, Integer age, String password, Boolean enabled) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
        this.enabled = enabled;
    }
}
