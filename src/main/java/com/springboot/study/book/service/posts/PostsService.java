package com.springboot.study.book.service.posts;

import com.springboot.study.book.domain.posts.Posts;
import com.springboot.study.book.domain.posts.PostsRepository;
import com.springboot.study.book.web.dto.PostsListResponseDto;
import com.springboot.study.book.web.dto.PostsResponseDto;
import com.springboot.study.book.web.dto.PostsSaveRequestDto;
import com.springboot.study.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);  // 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제함
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    // 트랜잭션 어노테이션 @Transactional에 (readOnly = true)를 주면 트랜잭션 범위는 유지되고,
    // 조회 기능만 남겨둬 조회 속도가 개선된다.
    // (등록, 수정, 삭제 기능이 없는 서비스 메소드에 사용하는 것이 좋다.)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        /* .map(PostsListResponseDto::new) 람다식 : .map(posts -> new PostsListResponseDto(posts))
           : postsRepository 결과 넘어온 Posts의 Stream을 map을 통해 PostsListReponseDto로 변환하고 List로 반환하는 메소드
         */
    }
}