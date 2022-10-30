package com.example.Spring_Sec_Login_Test.ropository_service;

import com.example.Spring_Sec_Login_Test.entity_dto.UserDTO;
import com.example.Spring_Sec_Login_Test.entity_dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public String save(UserDTO dto){
        //비밀번호 BCryptPasswordEncoder방식으로 암호화
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //암호화한 비밀번호를 dto에 세팅
        dto.setPassword(encoder.encode(dto.getPassword()));
        // dto를 entity타입으로 바꿔서 데이터베이스에 접근, 저장
        return repository.save(UserInfo.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .auth(dto.getAuth())
                .build()
        ).getUserId();
    }



    //  ================================Override==================================
//  스프링 시큐리티는 loadUserByUsername 메서드에 의해 리턴된 User 객체의 비밀번호가 화면으로부터 입력 받은 비밀번호와 일치하는지를 검사하는 로직을 내부적으로 가지고 있다.
    @Override
    public UserInfo loadUserByUsername(String user_id) throws UsernameNotFoundException {
        return repository.findByUserId(user_id)
                .orElseThrow(() -> new UsernameNotFoundException("아이디를 찾을 수 없습니다!"));
    }
}
