package com.example.qna.qna.dto;


import com.example.qna.qna.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QnaPageDto<T> {
    private T data;
    private PageInfo pageInfo;

}
