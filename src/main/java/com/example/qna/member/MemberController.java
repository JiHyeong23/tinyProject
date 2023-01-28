package com.example.qna.member;

import com.example.qna.member.dto.MemberLoginDto;
import com.example.qna.member.dto.MemberPatchDto;
import com.example.qna.member.dto.MemberPostDto;
import com.example.qna.member.dto.MemberResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/members")
public class MemberController {
    private MemberService memberService;
    private MemberRepository memberRepository;
    private MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @PostMapping
    public ResponseEntity PostMember(@RequestBody MemberPostDto memberPostDto) {
        memberService.memberSave(memberPostDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberLoginDto memberLoginDto) {
        MemberResponseDto memberResponseDto = memberService.memberLogin(memberLoginDto);
        if(memberResponseDto == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(memberResponseDto, HttpStatus.OK);
    }


    @GetMapping("/{member-id}")
    public ResponseEntity getMember (@PathVariable(name="member-id") Long memberId) {
        MemberResponseDto member = memberService.findMember(memberId);
        return new ResponseEntity(member, HttpStatus.OK);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember (@RequestBody MemberPatchDto memberPatchDto,
                                       @PathVariable(name="member-id") Long memberId){
        memberService.memberPatch(memberPatchDto, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember (@PathVariable(name="member-id") Long memberId) {
        memberService.memberDelete(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
