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
        UserEntity data = new UserEntity(); // <-회원 객체를 새로 생성
        data.setUsername(joinDTO.getUsername()); // <- 회원 객체에 id 넣기
        // 아래가 엔코더를 이용하여 해쉬화
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        // BCrypt를 사용한 해쉬화된 password 를 넣기

        data.setRole("ROLE_USER");
        // 회원 티입을 강제로 우리가 넣어준것

        userRepository.save(data);
        // 레포지토리의 save 기능을 사용하여 data 객체를 넣어주기
    }
}
