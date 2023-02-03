package com.example.qna.qna.service;

import com.example.qna.qna.QNA;
import com.example.qna.qna.dto.*;
import org.springframework.data.domain.Page;

public interface QnaService {
    void questionSave(QnaPostDto qnaPostDto);
    void answerSave(AnswerPostDto answerPostDto, Long questionId);
    void deleteQna(Long questionId);
    void patchQuestion(QuestionPatchDto questionPatchDto, Long questionId);
    void patchAnswer(AnswerPostDto answerPostDto, Long questionId);
    void qnaLike(Long questionId, Long memberId);
    QnAsResponseDto findQna(Long questionId, Long memberId);
    Page<QNA> findQnas(int page, int size);
    Page<QNA> searchQna(String searchString, int page, int size);




}
