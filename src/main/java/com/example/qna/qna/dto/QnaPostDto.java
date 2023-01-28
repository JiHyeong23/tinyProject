package com.example.qna.qna.dto;

import com.example.qna.qna.enums.Secret;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class QnaPostDto {

    @NotBlank
    private Long memberId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private Secret secret;

}
