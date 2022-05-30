package com.springboot.study.book;

import com.springboot.study.book.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
// SpringBoot Test와 JUnit 사이에 연결자 역할을 한다.
// 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자인 SpringRunner라는 스프링 실행자를 실행시킨다.
// JUnit4를 사용하면 @RunWith(SpringRunner.class)를 작성해야 어노테이션이 무시되지 앉지만 JUnit5를 사용하면 @RunWith, @ExtendWith 등을 추가할 필요가 없다.
@WebMvcTest(HelloController.class)
// Web Layer만 테스트할 때 사용하며, 특정 컨트롤러만 인스턴스화 해 테스트하는 것도 가능하다.
// @Controller, @ControllerAdvice 등을 사용할 수 있다.
public class HelloControllerTest {

    @Autowired  // 스프링이 관리하는 Bean을 주입받는다.
    private MockMvc mvc;    // 웹 API를 테스트할 때 사용하며, 스프링 MVC 테스트의 시작점이다.
                            // 해당 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                // mvc.perform의 결과와 HTTP Header의 Status를 검증한다.
                // OK 즉, 200인지 아닌지를 검증한다.
                .andExpect(content().string(hello));
                // mvc.perform의 결과와 응답 본문의 내용을 검증한다.
                //Controller에서 “hello”를 리턴하기 때문에 이 값이 맞는지 검증한다.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.valueOf(amount)))
                        /* param : API 테스트할 때 사용될 요청 파라미터를 설정한다.(String 값만 허용)
                                   숫자/날짜 등의 데이터를 등록하기 위해선 문자열로 변경해야 한다.
                         */
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
                /* jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드이다.
                              $를 기준으로 필드명 명시한다.
                */

    }
}