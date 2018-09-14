package com.albago.webservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query("SELECT c FROM Comments c WHERE c.post_id=:post_id")
    List<Comments> findComments(@Param("post_id") Long post_id);

    @Query("SELECT c.pwd FROM Comments c WHERE c.comment_id=:comment_id")
    String findPwd(@Param("comment_id") Long comment_id);

    @Query("SELECT c.favor FROM Comments c WHERE c.comment_id=:comment_id")
    String findFavor(@Param("comment_id") Long comment_id);

    @Modifying
    @Query("UPDATE Comments as c SET c.favor=:favor WHERE c.comment_id=:comment_id")
    int updateFavor(@Param("favor") int favor,
                       @Param("comment_id") Long comment_id);
}