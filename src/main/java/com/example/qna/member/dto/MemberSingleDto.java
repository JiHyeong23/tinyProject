package com.example.qna.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberSingleDto<T> {
    private T data;
}
