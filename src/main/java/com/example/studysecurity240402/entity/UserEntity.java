package com.example.studysecurity240402.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String username;
    private String password;

    private String role;

}
