package com.example.qna.qna.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@ToString
public class AnswerPostDto {
    @NotBlank
    private Long memberId;
    @NotBlank
    private String content;
}
