package com.example.qna.qnaLike;

import com.example.qna.qna.QNA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaLikeRepository extends JpaRepository<QnaLike, Long> {

    Long countByQna(QNA qna);
}