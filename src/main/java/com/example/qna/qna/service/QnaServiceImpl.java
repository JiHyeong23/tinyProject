package com.example.qna.qna.service;

import com.example.qna.member.Member;
import com.example.qna.member.MemberRepository;
import com.example.qna.qna.QNA;
import com.example.qna.qna.dto.AnswerPostDto;
import com.example.qna.qna.dto.QnaResponseDto;
import com.example.qna.qna.dto.QuestionPatchDto;
import com.example.qna.qna.enums.Category;
import com.example.qna.qna.enums.QuestionStatus;
import com.example.qna.qna.mapper.QnaMapper;
import com.example.qna.qna.QnaRepository;
import com.example.qna.qna.dto.QnaPostDto;
import com.example.qna.qnaLike.QnaLike;
import com.example.qna.qnaLike.QnaLikeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;

@Service
@NoArgsConstructor
public class QnaServiceImpl implements QnaService {

    private QnaMapper qnaMapper;
    private QnaRepository qnaRepository;
    private MemberRepository memberRepository;
    private QnaLikeRepository qnaLikeRepository;

    @Autowired
    public QnaServiceImpl(QnaMapper qnaMapper, QnaRepository qnaRepository,
                          MemberRepository memberRepository, QnaLikeRepository qnaLikeRepository) {
        this.qnaMapper = qnaMapper;
        this.qnaRepository = qnaRepository;
        this.memberRepository = memberRepository;
        this.qnaLikeRepository = qnaLikeRepository;
    }

    @Override
    public void questionSave(QnaPostDto qnaPostDto) {
        QNA qna = qnaMapper.qnaPostDtoToQNA(qnaPostDto);
        String time = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHssSSS");
        Calendar dateTime = Calendar.getInstance();
        time = sdf.format(dateTime.getTime());
        String random = String.valueOf((int)(Math.random()*10000));
        Long unique = Long.parseLong(time + random);

        qna.setGroup_id(unique);
        qna.setQuestionStatus(QuestionStatus.QUESTION_REGISTRATION);
        qna.setCategory(Category.QUESTION);
        qna.setCreatedAt(LocalDateTime.now());
        qna.setViewCount(0L);

        qnaRepository.save(qna);
    }

    @Override
    public void answerSave(AnswerPostDto answerPostDto, Long questionId) {
        QNA qna = qnaMapper.answerPostDtoToQNA(answerPostDto);
        QNA question = qnaRepository.findById(questionId).get();
        qna.setGroup_id(question.getGroup_id());
        question.setQuestionStatus(QuestionStatus.QUESTION_ANSWERED);
        qna.setCategory(Category.ANSWER);
        qna.setCreatedAt(LocalDateTime.now());

        qnaRepository.save(qna);
        qnaRepository.save(question);
    }
    @Override
    public void patchQuestion(QuestionPatchDto questionPatchDto, Long questionId) {
        QNA qna = qnaRepository.findById(questionId).get();
        if(questionPatchDto.getTitle() != null) {
            qna.setTitle(questionPatchDto.getTitle());
        }
        if(questionPatchDto.getContent() != null) {
            qna.setContent(questionPatchDto.getContent());
        }
        if(questionPatchDto.getSecret() != null) {
            qna.setSecret(questionPatchDto.getSecret());
        }
        qna.setModifiedAt(LocalDateTime.now());
        qnaRepository.save(qna);
    }

    @Override
    public void patchAnswer(AnswerPostDto answerPostDto, Long questionId) {
        QNA qna = qnaRepository.findById(questionId).get();
        if(answerPostDto.getContent() != null) {
            qna.setContent(answerPostDto.getContent());
        }
        qna.setModifiedAt(LocalDateTime.now());
        qnaRepository.save(qna);
    }

    @Override
    public QnaResponseDto findQna(Long questionId) {
        QNA qna = qnaRepository.findById(questionId).get();
        QnaResponseDto qnaResponseDto = qnaMapper.qnaToDto(qna);

        Long viewCount = qna.getViewCount();
        qnaResponseDto.setViewCount(++viewCount);

        qna.setViewCount(viewCount);
        qnaRepository.save(qna);

        return qnaResponseDto;
    }

    @Override
    public Page<QNA> findQnas(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return qnaRepository.findByQuestionStatusNotAndCategoryNotOrderByCreatedAtDesc(QuestionStatus.QUESTION_DELETE, Category.ANSWER, pageRequest);
    }

    @Override
    public void deleteQna(Long questionId) {
        QNA qna = qnaRepository.findById(questionId).get();
        qna.setQuestionStatus(QuestionStatus.QUESTION_DELETE);
        qnaRepository.save(qna);
    }

    @Override
    public void qnaLike(Long questionId, Long memberId) {
        QNA qna = qnaRepository.findById(questionId).get();
        Member member = memberRepository.findById(memberId).get();
        QnaLike qnaLike = new QnaLike(member, qna);
        qnaLikeRepository.save(qnaLike);
    }
}
