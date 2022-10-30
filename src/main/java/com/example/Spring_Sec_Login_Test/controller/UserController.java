package com.example.Spring_Sec_Login_Test.controller;

import com.example.Spring_Sec_Login_Test.entity_dto.UserDTO;
import com.example.Spring_Sec_Login_Test.ropository_service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

//===================================GET 요청===================================
    @GetMapping
    public String mainPage(){
        return "MainPage";
    }

    @GetMapping("/signup")
    public String signUpPage(){
        return "SignUpPage";
    }

    @GetMapping("/user")
    public String userPage(){
        return "UserPage";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "AdminPage";
    }

    @GetMapping("/accessdenied")
    public String accessDeniedPage(){return "AccessDeniedPage";}
//===================================POST 요청===================================

    // 회원가입
    @PostMapping("/signup")
    public String signUp(UserDTO dto) {
        service.save(dto);
        return "redirect:/";
    }

}
