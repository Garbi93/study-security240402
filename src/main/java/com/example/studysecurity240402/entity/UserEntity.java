package com.example.studysecurity240402.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter // <-lombok 기능
@Getter // <-lombok 기능
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // id 가 자동 생성 되고 자동 증가 되게
    private int id;

    @Column(unique = true) // <- 중복 방지 설정
    private String username;
    private String password;

    private String role;

}
