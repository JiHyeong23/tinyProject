package com.example.qna.member.dto;

import com.example.qna.member.enums.Role;
import com.example.qna.member.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberResponseDto {
    private Long memberId;
    private String email;
    private String pw;
    private String name;
    private Status member_Status;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
