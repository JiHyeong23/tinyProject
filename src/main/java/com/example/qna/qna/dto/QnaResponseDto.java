package com.example.qna.qna.dto;

import com.example.qna.qna.enums.QuestionStatus;
import com.example.qna.qna.enums.Secret;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class QnaResponseDto {
    private Long qnaId;
    private String title;
    private String content;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long likeCount;
    private boolean isNew;
    private Long viewCount;
    private Secret secret;
    private QuestionStatus questionStatus;
    private boolean isLike;
}
