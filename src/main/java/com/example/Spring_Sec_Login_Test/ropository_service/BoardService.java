package com.example.Spring_Sec_Login_Test.ropository_service;

import com.example.Spring_Sec_Login_Test.entity_dto.Board;
import com.example.Spring_Sec_Login_Test.entity_dto.BoardDTO;
import com.example.Spring_Sec_Login_Test.entity_dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    private BoardDTO entityToDto(Board entity){
        BoardDTO dto = BoardDTO.builder()
                .boardId(entity.getBoardId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter().getUserId())
                .createDate(entity.getCreateDate())
                .modiDate(entity.getModiDate())
                .build();
        return dto;
    }

    public List<BoardDTO> getAllBoard(){
        Function<Board,BoardDTO> fn = (entity->entityToDto(entity));
        List<Board> boards = repository.findAll();
        List<BoardDTO> boardList = boards.stream().map(fn).collect(Collectors.toList());
        return boardList;
    }
    public void post(BoardDTO dto){
        UserInfo writeUser = UserInfo.builder().userId(dto.getWriter()).build();
        Board entity = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(writeUser)
                .build();
        repository.save(entity);
    }
}
