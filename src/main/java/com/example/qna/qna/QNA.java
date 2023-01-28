package com.example.qna.qna;

import com.example.qna.member.Member;
import com.example.qna.qna.enums.Category;
import com.example.qna.qna.enums.QuestionStatus;
import com.example.qna.qna.enums.Secret;
import com.example.qna.qnaLike.QnaLike;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class QNA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qnaId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private QuestionStatus questionStatus;
    private Secret secret;
    private Long group_id;
    private Category category;
    private Long viewCount;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

//    List<QnaImage> images (OTM);

    @OneToMany(mappedBy = "qna")
    private List<QnaLike> likes = new ArrayList<>();

}
