package com.example.qna.qnaImage;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class QnaImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long image_id;
    String url;
  //  QNA qna(FK);
}
