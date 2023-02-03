package com.example.qna.qna;

import com.example.qna.qna.dto.*;
import com.example.qna.qna.mapper.QnaMapper;
import com.example.qna.qna.service.QnaService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/qna")
public class QnaController {

    private QnaService qnaService;
    private QnaMapper qnaMapper;
    private final QnaRepository qnaRepository;

    public QnaController(QnaService qnaService, QnaMapper qnaMapper, QnaRepository qnaRepository) {
        this.qnaService = qnaService;
        this.qnaMapper = qnaMapper;
        this.qnaRepository = qnaRepository;
    }

    @PostMapping
    public ResponseEntity questionPost(@RequestBody QnaPostDto qnaPostDto) {
        qnaService.questionSave(qnaPostDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{qna-id}")
    public ResponseEntity answerPost(@RequestBody AnswerPostDto answerPostDto,
                                     @PathVariable(name="qna-id") Long questionId) {
        qnaService.answerSave(answerPostDto, questionId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{qna-id}")
    public ResponseEntity patchQuestion(@RequestBody QuestionPatchDto questionPatchDto,
                                        @PathVariable(name="qna-id") Long questionId) {
        qnaService.patchQuestion(questionPatchDto, questionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/answer/{qna-id}")
    public ResponseEntity patchAnswer(@RequestBody AnswerPostDto answerPostDto,
                                      @PathVariable(name="qna-id") Long answerId) {
        qnaService.patchAnswer(answerPostDto, answerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/{qna-id}")
//    public ResponseEntity getQna(@PathVariable(name="qna-id") Long questionId) {
//        QnAsResponseDto qna = qnaService.findQna(questionId);
//        return new ResponseEntity<>(qna, HttpStatus.OK);
//    }
    @GetMapping("/{qna-id}/{member-id}")
    public ResponseEntity getQna(@PathVariable(name="qna-id") Long questionId,
                                 @PathVariable(name="member-id") Long memberId) {
        QnAsResponseDto qna = qnaService.findQna(questionId, memberId);
        return new ResponseEntity<>(qna, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQnas(@Positive @RequestParam int page,
                                  @Positive @RequestParam int size) {
        Page<QNA> qnaPage = qnaService.findQnas(page-1, size);
        PageInfo pageInfo = new PageInfo(page-1, size, (int)qnaPage.getTotalElements(), qnaPage.getTotalPages());

        List<QNA> qnas = qnaPage.getContent();
        List<QnaResponseDto> response = qnaMapper.qnasToQnaResponseDtos(qnas);

        return new ResponseEntity<>(new QnaPageDto(response, pageInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{qna-id}")
    public ResponseEntity deleteQna(@PathVariable(name = "qna-id") Long questionId) {
        qnaService.deleteQna(questionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/likes/{qna-id}/{member-id}")
    public ResponseEntity qnaLike(@PathVariable(name="qna-id") Long questionId,
                                  @PathVariable(name="member-id") Long memberId) {
        qnaService.qnaLike(questionId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity qnaSearch(@RequestParam(name="keyword") String searchString,
                                    @Positive @RequestParam int page,
                                    @Positive @RequestParam int size) {
        Page<QNA> qnaPage = qnaService.searchQna(searchString, page-1, size);
        PageInfo pageInfo = new PageInfo(page-1, size, (int)qnaPage.getTotalElements(), qnaPage.getTotalPages());

        List<QNA> qnas = qnaPage.getContent();
        List<QnaResponseDto> response = qnaMapper.qnasToQnaResponseDtos(qnas);

        return new ResponseEntity<>(new QnaPageDto(response, pageInfo), HttpStatus.OK);
    }
}
