package com.example.studysecurity240402.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDTO {
    private String username;
    private String password;
}
// 필요한 필드만 넣어서 사용 DB에 필요한 정보인 id 같은 정보 없이 클라이언트에서 넣어준 정보만