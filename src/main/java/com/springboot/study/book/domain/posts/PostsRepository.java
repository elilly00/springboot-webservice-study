package com.springboot.study.book.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    // @Query를 사용하면 가독성이 좋아진다.
    List<Posts> findAllDesc();
}
