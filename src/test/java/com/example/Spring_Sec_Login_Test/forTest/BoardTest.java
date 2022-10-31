package com.example.Spring_Sec_Login_Test.forTest;

import com.example.Spring_Sec_Login_Test.entity_dto.Board;
import com.example.Spring_Sec_Login_Test.entity_dto.BoardDTO;
import com.example.Spring_Sec_Login_Test.ropository_service.BoardRepository;
import com.example.Spring_Sec_Login_Test.ropository_service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class BoardTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void postingTest(){
        BoardDTO dto = BoardDTO.builder()
                        .writer("user")
                        .title("[title_user] 1")
                        .content("[content_user] 1")
                        .build();
        boardService.post(dto);
    }

    @Test
    @Transactional
    public void getAllBoard(){
        List<Board> boards = boardRepository.findAll();
        for(Board board : boards){
            System.out.println(board);
        }
    }
}
