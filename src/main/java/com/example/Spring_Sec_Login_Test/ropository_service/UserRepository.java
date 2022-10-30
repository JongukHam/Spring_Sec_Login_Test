package com.example.Spring_Sec_Login_Test.ropository_service;

import com.example.Spring_Sec_Login_Test.entity_dto.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo,String> {

    Optional<UserInfo> findByUserId(String user_id);
}
