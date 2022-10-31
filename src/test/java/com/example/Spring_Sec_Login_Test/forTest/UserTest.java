package com.example.Spring_Sec_Login_Test.forTest;

import com.example.Spring_Sec_Login_Test.entity_dto.UserInfo;
import com.example.Spring_Sec_Login_Test.ropository_service.UserRepository;
import com.example.Spring_Sec_Login_Test.ropository_service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void getAllUser(){
        List<UserInfo> users = userRepository.findAll();
        for(UserInfo user : users){
            System.out.println(user);
        }
    }
}
