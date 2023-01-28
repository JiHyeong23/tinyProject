package com.example.qna.member;

import com.example.qna.qna.QNA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmailAndPw(String email, String pw);
}
