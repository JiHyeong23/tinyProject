package com.example.qna.member;

import com.example.qna.qna.PageInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPageDto<T> {

    private T data;
    private PageInfo pageInfo;

}
