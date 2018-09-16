package com.albago.webservice.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC ")
    Stream<Posts> findAllDesc();

    @Query("SELECT p.favor FROM Posts p WHERE p.postId=:postId")
    String findFavor(@Param("postId") Long post_id);

    @Query("SELECT p.hate FROM Posts p WHERE p.postId=:postId")
    String findHate(@Param("postId") Long post_id);

    @Query("SELECT p FROM Posts p WHERE p.cat_id=:catId")
    Page<Posts> findPostsByCat_id(@Param("catId") int catId, Pageable pageable);

    @Modifying
    @Query("UPDATE Posts as p SET p.favor=:favor WHERE p.postId=:postId")
    int updateFavor(@Param("favor") int favor,
                              @Param("postId") Long post_id);

    @Modifying
    @Query("UPDATE Posts as p SET p.hate=:hate WHERE p.postId=:postId")
    int updateHate(@Param("hate") int hate,
                   @Param("postId") Long post_id);

    @Query("SELECT p.pwd FROM Posts p WHERE p.postId=:postId")
    String findPwd(@Param("postId") Long post_id);

}



