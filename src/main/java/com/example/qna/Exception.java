package com.example.qna;

import lombok.Getter;

@Getter
public enum Exception {
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    PASSWORD_NOT_MATCH(405, "Password Not Match"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    Exception(int i, String s) {

    }
}
