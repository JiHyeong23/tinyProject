package com.example.qna.qna.dto;

import com.example.qna.qna.enums.Secret;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionPatchDto {

    private Long memberId;
    private String title;
    private String content;
    private Secret secret;

}
