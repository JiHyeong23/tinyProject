package com.example.qna.qna;

import com.example.qna.qna.enums.Category;
import com.example.qna.qna.enums.QuestionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnaRepository extends JpaRepository<QNA, Long> {
    Page<QNA> findByQuestionStatusNotAndCategoryNotOrderByCreatedAtDesc(QuestionStatus questionStatus, Category category, Pageable pageable);
    Page<QNA> findByTitleContainingOrContentContainingAndQuestionStatusNotAndCategoryNotOrderByCreatedAtDesc(
            String title, String content, QuestionStatus questionStatus, Category category, Pageable pageable);
    List<QNA> findByGroupIdAndCategory(Long GroupId, Category category);

}
