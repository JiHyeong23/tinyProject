package com.example.qna.member;

import com.example.qna.member.dto.MemberLoginDto;
import com.example.qna.member.dto.MemberPatchDto;
import com.example.qna.member.dto.MemberPostDto;
import com.example.qna.member.dto.MemberResponseDto;
import com.example.qna.member.enums.Role;
import com.example.qna.member.enums.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberRepository;
    private MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public void memberSave(MemberPostDto memberPostDto) {
        Member member = memberMapper.memberPostDtoToMember(memberPostDto);

        member.setMember_Status(Status.MEMBER_ACTIVE);
        member.setCreatedAt(LocalDateTime.now());
        member.setRole(Role.ROLE_USER);

        memberRepository.save(member);
    }

    public MemberResponseDto findMember(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        MemberResponseDto memberResponseDto = memberMapper.memberTomemberResponseDto(member);

        return memberResponseDto;
    }

    public void memberPatch(MemberPatchDto memberPatchDto, Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        if(memberPatchDto.getPw() != null) {
            member.setPw(memberPatchDto.getPw());
        }
        if(memberPatchDto.getName() != null) {
            member.setName(memberPatchDto.getName());
        }
        memberRepository.save(member);
    }

    public MemberResponseDto memberLogin(MemberLoginDto memberLoginDto) {
        Member member = memberRepository.findByEmailAndPw(memberLoginDto.getEmail(), memberLoginDto.getPw());
        if(member == null) {
            return null;
        }
        MemberResponseDto memberResponseDto = memberMapper.memberTomemberResponseDto(member);
        return memberResponseDto;
    }

    public void memberDelete(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        member.setMember_Status(Status.MEMBER_QUIT);
        memberRepository.save(member);
    }
}
