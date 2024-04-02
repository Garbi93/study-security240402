package com.example.studysecurity240402.controller;

import com.example.studysecurity240402.dto.JoinDTO;
import com.example.studysecurity240402.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    @Autowired
    private JoinService joinService; // <- 서비스 코드 주입 받기


    @GetMapping("/join")
    public String joinP() {
        return "join";
    }

    // 회원 가입 요청이 들어오는 부분
    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) { //<- 프론트에서 전달한 데이터를 받기
        System.out.println(joinDTO.getUsername());

        joinService.joinProcess(joinDTO); // <- service 의 해당 기능에 데이터 전달

        return "redirect:/login"; // <- 잘 작동하면 해당 경로로 리다이렉트
    }
}
