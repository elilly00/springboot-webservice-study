package com.springboot.study.book.web;

import com.springboot.study.book.service.posts.PostsService;
import com.springboot.study.book.web.dto.PostsResponseDto;
import com.springboot.study.book.web.dto.PostsSaveRequestDto;
import com.springboot.study.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")   // 게시글 등록 API
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")   // 게시글 수정 API
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")    // 게시글 삭제 API
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
    
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
