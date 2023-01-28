package com.example.qna.qna.mapper;

import com.example.qna.qna.QNA;
import com.example.qna.qna.dto.AnswerPostDto;
import com.example.qna.qna.dto.QnaPostDto;
import com.example.qna.qna.dto.QnaResponseDto;
import com.example.qna.qna.dto.QuestionPatchDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


public interface QnaMapper {
    QNA qnaPostDtoToQNA(QnaPostDto qnaPostDto);
    QNA answerPostDtoToQNA(AnswerPostDto answerPostDto);
    QnaResponseDto qnaToDto(QNA qna);
    List<QnaResponseDto> qnasToQnaResponseDtos(List<QNA> qnas);

}
