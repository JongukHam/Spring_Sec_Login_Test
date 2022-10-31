package com.example.Spring_Sec_Login_Test.entity_dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardDTO {

    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modiDate;
    private String writer;
}
