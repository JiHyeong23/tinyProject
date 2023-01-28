package com.example.qna.member;

import com.example.qna.member.enums.Role;
import com.example.qna.member.enums.Status;
import com.example.qna.qna.QNA;
import com.example.qna.qnaLike.QnaLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;
    private String email;
    private String pw;
    private String name;
    private Role role;
    private Status member_Status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    @OneToMany(mappedBy = "member")
    private List<QNA> qnas = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<QnaLike> likes = new ArrayList<>();

   // List<QnaLike> likes (OTM)
}
