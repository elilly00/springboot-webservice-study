package com.springboot.study.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing  // JPA Auditing 활성화
@SpringBootApplication
// 스프링 부트의 자동 설정, 스프링 Bean 읽기&생성을 모두 자동으로 설정
// 해당 클래스는 항상 프로젝트의 최상단에 위치해야 한다.
public class Application {  // 앞으로 만들 프로젝트의 메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
