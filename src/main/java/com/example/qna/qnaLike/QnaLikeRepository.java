package com.example.qna.qnaLike;

import com.example.qna.member.Member;
import com.example.qna.qna.QNA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaLikeRepository extends JpaRepository<QnaLike, Long> {

    Long countByQna(QNA qna);

    QnaLike findByQnaAndMember(QNA qna, Member member);
    void deleteByQnaAndMember(QNA qna, Member member);

    List<QnaLike>findByMember(Member member);
}
