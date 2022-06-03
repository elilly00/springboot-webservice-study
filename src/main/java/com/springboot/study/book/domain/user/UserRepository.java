package com.springboot.study.book.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);   
    // findByEmail : 소셜 로그인으로 반환되는 값 중 email을 통해 이미 존재하는 사용자인지 아닌지 구분하기 위한 메소드
}
