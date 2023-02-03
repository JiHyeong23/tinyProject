package com.example.qna.qnaLike;

import com.example.qna.member.Member;
import com.example.qna.qna.QNA;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class QnaLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long like_id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QNA_ID")
    private QNA qna;

    public QnaLike(Member member, QNA qna) {
        this.member = member;
        this.qna = qna;
    }
}
