package com.albago.webservice.domain;

import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query("SELECT c FROM Comments c WHERE c.post_id=:post_id ORDER BY c.comment_id ASC")
    List<Comments> findComments(@Param("post_id") Long post_id);

    @Query("SELECT c.pwd FROM Comments c WHERE c.comment_id=:comment_id")
    String findPwd(@Param("comment_id") Long comment_id);

    @Query("SELECT c.favor FROM Comments c WHERE c.comment_id=:comment_id")
    String findFavor(@Param("comment_id") Long comment_id);

    @Query("SELECT c.hate FROM Comments c WHERE c.comment_id=:comment_id")
    String findHate(@Param("comment_id") Long comment_id);

    @Modifying
    @Query("UPDATE Comments as c SET c.favor=:favor WHERE c.comment_id=:comment_id")
    int updateFavor(@Param("favor") int favor,
                       @Param("comment_id") Long comment_id);

    @Modifying
    @Query("UPDATE Comments as c SET c.hate=:hate WHERE c.comment_id=:comment_id")
    int updateHate(@Param("hate") int hate,
                   @Param("comment_id") Long comment_id);

    @Modifying
    @Query("DELETE FROM Comments c WHERE c.post_id=:post_id")
    void deleteCommentsByPostId(@Param("post_id") Long post_id);

    @Query("SELECT c.comment_id FROM Comments c WHERE post_id=:post_id")
    List<Long> getCommentId(@Param("post_id") Long post_id);

    @Query("SELECT c.favor FROM Comments c WHERE c.comment_id=:comment_id")
    int getFavor(@Param("comment_id") Long comment_id);

    @Query("SELECT c.hate FROM Comments c WHERE c.comment_id=:comment_id")
    int getHate(@Param("comment_id") Long comment_id);
}