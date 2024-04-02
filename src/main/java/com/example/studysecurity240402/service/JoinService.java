package com.example.studysecurity240402.service;

import com.example.studysecurity240402.dto.JoinDTO;
import com.example.studysecurity240402.entity.UserEntity;
import com.example.studysecurity240402.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository; // <- 레포지토리 주입 받기

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {
        // 가입전 동일한 아이디가 있는지 확인 (중복 검증)
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername()); // <- 회원 이름을 검증 로직에 인자로 전달
        if (isUser) {
            return; // <- 이미 DB에 동일한 회원이 있다면 그냥 코드 탈출
        } // <- 존재하지 않으면 아래코드가 실행됨

        UserEntity data = new UserEntity(); // <-회원 객체를 새로 생성
        data.setUsername(joinDTO.getUsername()); // <- 회원 객체에 id 넣기
        // 아래가 엔코더를 이용하여 해쉬화
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        // BCrypt를 사용한 해쉬화된 password 를 넣기

        data.setRole("ROLE_ADMIN");
        // 회원 티입을 강제로 우리가 넣어준것

        userRepository.save(data);
        // 레포지토리의 save 기능을 사용하여 data 객체를 넣어주기
    }
}
