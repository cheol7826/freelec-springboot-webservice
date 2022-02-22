package com.spring.webservice.config.auth;

import com.spring.webservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    // 정적 리소스 파일은 그냥 접근, api/vi으로 오는 접근은 role을 체크한다
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/js/**", "/images/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    // 로그아웃 성공시 이동할 주소
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    // oauth2 로그인 후 동작할 UserService 객체를 등록한다 ( 로그인 후 처리할 동작들을 수행 )
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
    }
}
