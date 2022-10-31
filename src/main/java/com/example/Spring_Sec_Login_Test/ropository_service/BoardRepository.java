package com.example.Spring_Sec_Login_Test.ropository_service;

import com.example.Spring_Sec_Login_Test.entity_dto.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
}
