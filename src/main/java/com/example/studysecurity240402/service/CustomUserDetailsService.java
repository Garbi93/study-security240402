package com.example.studysecurity240402.service;

import com.example.studysecurity240402.dto.CustomUserDetails;
import com.example.studysecurity240402.entity.UserEntity;
import com.example.studysecurity240402.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    // 스프링 시큐리티에서 제공하는 UserDetailService 를 구현 한다.

    @Autowired
    private UserRepository userRepository; //<- 회원 레포지 토리를 주입 받아 사용 할 것이고

    // 구현을 하면 무조건 오버라이딩 해준다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userData = userRepository.findByUsername(username); // <- 회원 레포에서 findByUsername 에 인자를 전달
        if (userData != null) { // <- 해당 회원의 아이디와 비밀번호가 안 비어 있다면
            return new CustomUserDetails(userData); // <- 생성자에 바로 회원 정보를 전달
        }

        return null; // <- 비어 있다면 빈값을 전달
    }
}
