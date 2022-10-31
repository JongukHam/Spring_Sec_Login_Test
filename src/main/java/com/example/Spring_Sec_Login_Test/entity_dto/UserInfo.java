package com.example.Spring_Sec_Login_Test.entity_dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Builder
//@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements UserDetails {

    // 아이디
    @Id
    private String userId;
    // 비밀번호
    private String password;
    // 권한
    private String auth;

//    @Builder.Default
    @OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE)
    private List<Board> boards;

    // 테스트를 위한 toString
    @Override
    public String toString(){
        if(boards.isEmpty()){
            return "****** [userId = "+userId + " password = " + password + " auth = " + auth + "] [i don't have board] ******";
        }else{
            return "****** [userId = "+userId + " password = " + password + " auth = " + auth + "] [i have board] ******";
        }
    }

//  ================================Override==================================
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    // 아이디 반환
    @Override
    public String getUsername() {
        return userId;
    }

    // 비밀번호 반환
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
