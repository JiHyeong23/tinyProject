package com.example.qna.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberPostDto {
    @NotBlank
    private String email;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;

}
