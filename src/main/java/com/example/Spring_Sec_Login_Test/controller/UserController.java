package com.example.Spring_Sec_Login_Test.controller;

import com.example.Spring_Sec_Login_Test.entity_dto.BoardDTO;
import com.example.Spring_Sec_Login_Test.entity_dto.UserDTO;
import com.example.Spring_Sec_Login_Test.entity_dto.UserInfo;
import com.example.Spring_Sec_Login_Test.ropository_service.BoardService;
import com.example.Spring_Sec_Login_Test.ropository_service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final BoardService boardService;

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

    @GetMapping("/board")
    public String boardPage(Model model){
        model.addAttribute("boardList", boardService.getAllBoard());
        return "BoardPage";
    }

    @GetMapping("/write")
    public String writePage(){
        return "WritePage";
    }
//===================================POST 요청===================================

    // 회원가입
    @PostMapping("/signup")
    public String signUp(UserDTO dto) {
        service.save(dto);
        return "redirect:/";
    }

    // 글쓰기
    // @AuthenticationPrincipal 사용해서 현제 로그인된 객채(userDetailsService가 반환하는 객체)를 가지고 올 수 있다.
    @PostMapping("/write")
    public String posting(BoardDTO dto, @AuthenticationPrincipal UserInfo loginUser){
        dto.setWriter(loginUser.getUserId());
        boardService.post(dto);
        return "redirect:/board";
    }

}
