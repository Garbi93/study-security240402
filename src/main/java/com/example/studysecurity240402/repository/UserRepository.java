package com.example.studysecurity240402.repository;

import com.example.studysecurity240402.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
// JpaRepository 를 상속 받고                    DB와 연동되는 Entity, id의 타입
}
