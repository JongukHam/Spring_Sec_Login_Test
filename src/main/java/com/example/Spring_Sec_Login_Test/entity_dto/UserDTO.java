package com.example.Spring_Sec_Login_Test.entity_dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {

    private String userId;
    private String password;
    private String auth;
    private List<BoardDTO> boards;
}
