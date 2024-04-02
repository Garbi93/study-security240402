package com.example.studysecurity240402.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // <- 스프링 부트 에서 config 파일 인식 시키는 기능
@EnableWebSecurity // <- 시큐리티 기능을 활성화 하고 웹 보안 구성을 시작할 수 있다.
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // 클래스를 임폴트 해오기    메서드 명 사용
        return new BCryptPasswordEncoder();
        // 새로운 객체로 return 하기
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     인터페이스의 메서드를 사용           인자로 해당 타입 받기    예외처리

        // 이제 내부에 어떤 경로에 요청이 왔을때에 어떤 응답을 해줄지 설정 해주면 된다.
        // 작성방식은 람다식으로 해주면 된다.
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/","/login", "/loginProc", "/join", "/joinProc").permitAll()
                // 특정 경로에 요청을 넣고 싶을때 사용
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
        );

        // 권한(규칙)이 없는 사용자가 접근하게 되면 자동으로 로그인 페이지로 리다이렉션 하게 설정
        http.formLogin((auth)-> auth
                .loginPage("/login") // <- 로그인 페이지로 이동 시키기
                .loginProcessingUrl("/loginProc") // <- 프론트에서 로그인 데이터를 넘기면 security 가 받아 처리를 하게된다.
                .permitAll()
        );

        // 로그인을 하게 되면 csrf 라는 토큰이 필요한데 지금 과정에서는 disable 상태로 두고 개발함
        http.csrf((auth) -> auth.disable());

        // 다중 로그인 설정
        http.sessionManagement((auth) -> auth
                .maximumSessions(1) // <- 하나의 아이디에 동시접속 수
                .maxSessionsPreventsLogin(true) // <- true: 해당 값을 초과할 경우 새 로그인 차단
                // false: 기존 로그인 아웃 새로그인 진행
        );

        // 세션 고정 보호
        http.sessionManagement((auth) -> auth.sessionFixation().changeSessionId());

        return http.build(); // http 데이터를 받은 것을 build 로 리턴 해야한다.
    }


}
