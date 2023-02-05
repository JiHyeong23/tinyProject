package com.example.qna.member;

import com.example.qna.member.dto.*;
import com.example.qna.qna.PageInfo;
import com.example.qna.qna.QNA;
import com.example.qna.qna.dto.QnaPageDto;
import com.example.qna.qna.dto.QnaResponseDto;
import com.example.qna.qna.mapper.QnaMapper;
import com.example.qna.qna.mapper.QnaMapperImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private MemberService memberService;
    private QnaMapper qnaMapper;

    @PostMapping("/join")
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
        return new ResponseEntity(new MemberSingleDto<>(memberResponseDto), HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember (@PathVariable(name="member-id") Long memberId) {
        MemberResponseDto member = memberService.findMember(memberId);
        return new ResponseEntity(new MemberSingleDto<>(member), HttpStatus.OK);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember (@RequestBody MemberPatchDto memberPatchDto,
                                       @PathVariable(name="member-id") Long memberId){
        memberService.memberPatch(memberPatchDto, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember (@PathVariable(name="member-id") Long memberId,
                                        @RequestBody MemberLoginDto memberLoginDto) {
        memberService.memberDelete(memberId, memberLoginDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/qna/{member-id}")
    public ResponseEntity showMyPost (@PathVariable("member-id") Long memberId,
                                      @Positive @RequestParam int page,
                                      @Positive @RequestParam int size) {
//                                      @RequestParam Sort.Direction direction,
//                                      @RequestParam String searchString) {
        Page<QNA> memberPage = memberService.findMemberPost(memberId, page-1, size);
        List<QNA> memberPosts = memberPage.getContent();
        List<QnaResponseDto> response = qnaMapper.qnasToQnaResponseDtos(memberPosts);
        PageInfo pageInfo = new PageInfo(page-1, size, (int)memberPage.getTotalElements(), memberPage.getTotalPages());

        return new ResponseEntity<>(new QnaPageDto<>(response, pageInfo), HttpStatus.OK);
    }

    @GetMapping("/likes/{member-id}")
    public ResponseEntity showLikedPost (@PathVariable("member-id") Long memberId,
                                         @Positive @RequestParam int page,
                                         @Positive @RequestParam int size) {
        Page<QNA> memberPage = memberService.findLikedPost(memberId, page-1, size);
        List<QNA> memberPosts = memberPage.getContent();
        List<QnaResponseDto> response = qnaMapper.qnasToQnaResponseDtos(memberPosts);
        PageInfo pageInfo = new PageInfo(page-1, size, (int)memberPage.getTotalElements(), memberPage.getTotalPages());

        return new ResponseEntity<>(new QnaPageDto<>(response, pageInfo), HttpStatus.OK);
    }

}
