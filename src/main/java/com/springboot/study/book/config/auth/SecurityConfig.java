package com.springboot.study.book.config.auth;

import com.springboot.study.book.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정들을 활성화 시킨다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                // h2-console 화면을 사용하기 위해 해당 옵션들을 disable한다.

                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    // authorizeRequests : URL별 권한 관리를 설정하는 옵션의 시작점
                    //                     authorizeRequests가 선언되어야 antMatchers 옵션을 사용할 수 있다.
                    // antMatchers : 권한 관리 대상을 지정하는 옵션
                    //               URL, HTTP 메소드별로 관리가 가능하다.
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                    // anyRequest : 설정된 값들 이외 나머지 URL들을 나타낸다.
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                    // logout().logoutSuccessUrl(”/”) : 로그아웃 기능에 대한 여러 설정의 진입점
                    //                                  로그아웃 성공 시 / 주소로 이동한다.
                .and()
                    .oauth2Login()  // OAuth 2 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint() // 로그인 성공 이후 사용자 정보를 가져올 때의 설정들 담당
                            .userService(customOAuth2UserService);
                            // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록, 추가로 진행하고자 하는 기능을 명시할 수 있다.
    }
}
