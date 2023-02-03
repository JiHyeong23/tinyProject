package com.example.qna.qna;

import com.example.qna.member.Member;
import com.example.qna.qna.enums.Category;
import com.example.qna.qna.enums.QuestionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnaRepository extends JpaRepository<QNA, Long> {
    Page<QNA> findByQuestionStatusNotAndCategoryNotOrderByCreatedAtDesc(QuestionStatus questionStatus, Category category, Pageable pageable);
    Page<QNA> findByTitleContainingOrContentContainingAndQuestionStatusNotAndCategoryNotOrderByCreatedAtDesc(
            String title, String content, QuestionStatus questionStatus, Category category, Pageable pageable);
    List<QNA> findByGroupIdAndCategory(Long GroupId, Category category);
    Page<QNA> findByMember_memberIdAndCategoryNotAndQuestionStatusNotOrderByCreatedAtDesc(
            @Param(value="memberId")Long memberId, Category category, QuestionStatus questionStatus, Pageable pageable);
    List<QNA> findByGroupId(Long GroupId);
    Page<QNA> findByQnaIdIn(List<Long> likeList, Pageable pageable);
}
