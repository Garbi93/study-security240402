package com.example.studysecurity240402.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // <- 스프링 부트 에서 config 파일 인식 시키는 기능
@EnableWebSecurity // <- 시큐리티 기능을 활성화 하고 웹 보안 구성을 시작할 수 있다.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     인터페이스의 메서드를 사용           인자로 해당 타입 받기    예외처리

        // 이제 내부에 어떤 경로에 요청이 왔을때에 어떤 응답을 해줄지 설정 해주면 된다.
        // 작성방식은 람다식으로 해주면 된다.
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/","login").permitAll()
                // 특정 경로에 요청을 넣고 싶을때 사용
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
        );
        return http.build(); // http 데이터를 받은 것을 build 로 리턴 해야한다.
    }
}
