package com.springboot.study.book.dto;

import com.springboot.study.book.web.dto.HelloResponseDto;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 룸복_기능_테스트() {

        // given
        String name = "test";
        int amount = 1000;
        
        // when 
        HelloResponseDto dto = new HelloResponseDto(name, amount);
        
        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        /*
        [assertThat]
         assertj라는 테스트 검증 라이브러리의 검증 메소드이다. 검증하고 싶은 대상을 메소드 인자로 받는다.
         메소드 체이닝이 지원돼 isEqualTo와 같이 메소드를 이어서 사용할 수 있다.
        [isEqualTo]
         assertj의 동등 비교 메소드이다.
         assertThat에 있는 값과 isEqualTo의 값을 비교해 같을 때만 테스트에 성공한다.
        */
    }
}
