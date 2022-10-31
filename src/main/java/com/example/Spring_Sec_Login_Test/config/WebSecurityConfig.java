package com.example.Spring_Sec_Login_Test.config;

import com.example.Spring_Sec_Login_Test.ropository_service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService service;

    // 인증을 무시할 경로 지정(css,js,h2-console무시)
    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/css/**","/js/***","/img/**","/h2-console/**");
    }

    // http 관련 인증 설정
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests() // 접근 관련 인증설정
                    .antMatchers("/signup","/","/login").permitAll()
                    .antMatchers("/user","/board","write").hasRole("USER")
                    .antMatchers("/admin").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/user")
                .and()
                    .logout()
                    .logoutSuccessUrl("/main")
                    .invalidateHttpSession(true)
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/accessdenied") // 권한 없는 대상 접근시도 시 이동할 페이지
                ;
    }

    // 로그인시 필요한 정보 가져오는 메서드
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(service) // 로그인시 필요한 정보 가져오는 메서드
                .passwordEncoder(new BCryptPasswordEncoder()); // 설정한 passwordEncoder
    }
}
