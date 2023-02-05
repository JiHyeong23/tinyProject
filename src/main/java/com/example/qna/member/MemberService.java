package com.example.qna.member;

import com.example.qna.member.dto.MemberLoginDto;
import com.example.qna.member.dto.MemberPatchDto;
import com.example.qna.member.dto.MemberPostDto;
import com.example.qna.member.dto.MemberResponseDto;
import com.example.qna.member.enums.Role;
import com.example.qna.member.enums.Status;
import com.example.qna.qna.QNA;
import com.example.qna.qna.QnaRepository;
import com.example.qna.qna.enums.Category;
import com.example.qna.qna.enums.QuestionStatus;
import com.example.qna.qnaLike.QnaLike;
import com.example.qna.qnaLike.QnaLikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    private MemberRepository memberRepository;
    private MemberMapper memberMapper;
    private final QnaLikeRepository qnaLikeRepository;
    private final QnaRepository qnaRepository;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper,
                         QnaLikeRepository qnaLikeRepository,
                         QnaRepository qnaRepository) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.qnaLikeRepository = qnaLikeRepository;
        this.qnaRepository = qnaRepository;
    }

    public void memberSave(MemberPostDto memberPostDto) {
        Member member = memberMapper.memberPostDtoToMember(memberPostDto);

        member.setMember_Status(Status.MEMBER_ACTIVE);
        member.setCreatedAt(LocalDateTime.now());
        member.setRole(Role.ROLE_USER);

        memberRepository.save(member);
    }

    public MemberResponseDto memberLogin(MemberLoginDto memberLoginDto) {
        Member member = memberRepository.findByEmailAndPw(memberLoginDto.getEmail(), memberLoginDto.getPw());
        if(member == null) {
            return null;
        }
        if(member.getMember_Status() == Status.MEMBER_QUIT) {
            return null;
        }
        MemberResponseDto memberResponseDto = memberMapper.memberTomemberResponseDto(member);
        return memberResponseDto;
    }

    public MemberResponseDto findMember(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        MemberResponseDto memberResponseDto = memberMapper.memberTomemberResponseDto(member);

        return memberResponseDto;
    }

    public void memberPatch(MemberPatchDto memberPatchDto, Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        if(memberPatchDto.getPw() != null && !memberPatchDto.getPw().equals("")) {
            member.setPw(memberPatchDto.getPw());
        }
        if(memberPatchDto.getName() != null && !memberPatchDto.getName().equals("")) {
            member.setName(memberPatchDto.getName());
        }
        memberRepository.save(member);
    }

    public void memberDelete(Long memberId, MemberLoginDto memberLoginDto) {
        String pw = memberLoginDto.getPw();
        Member member = memberRepository.findById(memberId).get();
        if(member.getPw().equals(pw)) {
            member.setMember_Status(Status.MEMBER_QUIT);
            memberRepository.save(member);
        } //else
    }

    public Page<QNA> findMemberPost(Long memberId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return qnaRepository.findByMember_memberIdAndCategoryNotAndQuestionStatusNotOrderByCreatedAtDesc(
                memberId, Category.ANSWER, QuestionStatus.QUESTION_DELETE, pageRequest);
    }

    public Page<QNA> findLikedPost(Long memberId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Member member = memberRepository.findById(memberId).get();
        List<QnaLike> qnaLike = qnaLikeRepository.findByMember(member);
        List<Long> likeList = new ArrayList<>();
        for (QnaLike like : qnaLike) {
            likeList.add(like.getQna().getQnaId());
        }
        System.out.println(likeList);
        return qnaRepository.findByQnaIdInAndCategoryNotAndQuestionStatusNotOrderByCreatedAtDesc(likeList, Category.ANSWER, QuestionStatus.QUESTION_DELETE, pageRequest);
    }

}
