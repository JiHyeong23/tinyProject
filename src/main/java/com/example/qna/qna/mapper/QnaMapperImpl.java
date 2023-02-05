package com.example.qna.qna.mapper;

import com.example.qna.member.Member;
import com.example.qna.qna.QNA;
import com.example.qna.qna.QnaRepository;
import com.example.qna.qna.dto.AnswerPostDto;
import com.example.qna.qna.dto.QnaPostDto;
import com.example.qna.qna.dto.QnaResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class QnaMapperImpl implements QnaMapper {
    private final QnaRepository qnaRepository;

    public QnaMapperImpl(QnaRepository qnaRepository) {
        this.qnaRepository = qnaRepository;
    }

    @Override
    public QNA qnaPostDtoToQNA(QnaPostDto qnaPostDto) {
        QNA qna = new QNA();
        qna.setTitle(qnaPostDto.getTitle());
        qna.setContent(qnaPostDto.getContent());
        qna.setSecret(qnaPostDto.getSecret());
        Member member = new Member();
        qna.setMember(member);
        qna.getMember().setMemberId(qnaPostDto.getMemberId());
        return qna;
    }

    @Override
    public QNA answerPostDtoToQNA(AnswerPostDto answerPostDto) {
        QNA qna = new QNA();
        Member member = new Member();
        qna.setMember(member);
        qna.getMember().setMemberId(answerPostDto.getMemberId());
        qna.setContent(answerPostDto.getContent());
        return qna;
    }

    @Override
    public QnaResponseDto qnaToDto(QNA qna) {
        QnaResponseDto qnaResponseDto = new QnaResponseDto();
        qnaResponseDto.setQnaId(qna.getQnaId());
        qnaResponseDto.setTitle(qna.getTitle());
        qnaResponseDto.setContent(qna.getContent());
        qnaResponseDto.setName(qna.getMember().getName());
        qnaResponseDto.setModifiedAt(qna.getModifiedAt());
        qnaResponseDto.setSecret(qna.getSecret());
        qnaResponseDto.setQuestionStatus(qna.getQuestionStatus());
        qnaResponseDto.setViewCount(qna.getViewCount());
        qnaResponseDto.setLikeCount(qna.getLikes().stream().count());

        LocalDateTime create = qna.getCreatedAt();
        qnaResponseDto.setCreatedAt(create);
        qnaResponseDto.setNew(create.plusDays(3).isAfter(LocalDateTime.now()));
        //images, like 만들어야함
//        List<QnaLike> likeCount = qna.getLikes();

        return qnaResponseDto;
    }

    @Override
    public List<QnaResponseDto> qnasToQnaResponseDtos(List<QNA> qnas) {
        if(qnas == null) return null;

        List<QnaResponseDto> list = new ArrayList<>(qnas.size());
        for(QNA qna : qnas) {
            list.add(qnaToDto(qna));
        }

        return list;
    }

}
