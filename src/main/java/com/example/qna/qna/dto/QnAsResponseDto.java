package com.example.qna.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QnAsResponseDto<T> {
    private QnaResponseDto question;
    private T answers;

}
