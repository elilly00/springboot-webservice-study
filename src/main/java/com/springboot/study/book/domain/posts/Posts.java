package com.springboot.study.book.domain.posts;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동 생성하는 어노테이션
@NoArgsConstructor  // 기본 생성자 자동 추가 어노테이션 = public Posts(){}
@Entity // 테이블과 링크될 클래스임을 나타내는 어노테이션
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK 필드를 나타내는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    // PK의 생성 규칙을 나나태는 어노테이션
    private Long id;

    @Column(length = 500, nullable = false)
    // 테이블의 칼럼을 나타내는 어노테이션(선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 되지만 추가로 변경이 필요한 옵션이 있을 때 사용한다.)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    // 해당 클래스의 빌더 패턴 클래스를 생성하는 어노테이션
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함된다.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
