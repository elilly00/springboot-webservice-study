package com.springboot.study.book.web;

import com.springboot.study.book.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// JSON을 반환하는 컨트롤러로 만드는 어노테이션
public class HelloController {

    @GetMapping("/hello")
    // HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어주는 어노테이션
    // /hello로 요청이 오면 문자열 hello를 반환하는 기능을 갖게 됨
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        // @RequestParam : 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
        return new HelloResponseDto(name, amount);
    }
}
