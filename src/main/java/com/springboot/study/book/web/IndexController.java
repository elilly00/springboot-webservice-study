package com.springboot.study.book.web;

import com.springboot.study.book.domain.posts.PostsRepository;
import com.springboot.study.book.service.posts.PostsService;
import com.springboot.study.book.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        // Model : 서버 템블릿 엔진에서 사용할 수 있는 객체 저장 가능
        //         postsService.findAllDesc()로 가져온 결과는 posts로 index.mustache에 전달한다.
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);

        return "posts-update";
    }
}
