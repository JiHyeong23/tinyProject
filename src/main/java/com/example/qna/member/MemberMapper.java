package com.example.qna.member;

import com.example.qna.member.dto.MemberPatchDto;
import com.example.qna.member.dto.MemberPostDto;
import com.example.qna.member.dto.MemberResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    private final MemberRepository memberRepository;

    public MemberMapper(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member memberPostDtoToMember(MemberPostDto memberPostDto) {
        Member member = new Member();
        member.setEmail(memberPostDto.getEmail());
        member.setPw(memberPostDto.getPw());
        member.setName(memberPostDto.getName());
        return member;
    }

    public MemberResponseDto memberTomemberResponseDto(Member member) {
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setMemberId(member.getMemberId());
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setPw(member.getPw());
        memberResponseDto.setName(member.getName());
        memberResponseDto.setCreatedAt(member.getCreatedAt());
        return memberResponseDto;
    }

}
