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
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {
        // DB에 이미 동일한 username 을 가진 회원이 존재하는가?

        UserEntity data = new UserEntity();
        data.setUsername(joinDTO.getUsername());
        // 아래가 엔코더를 이용하여 해쉬화
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
