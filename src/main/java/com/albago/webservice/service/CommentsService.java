package com.albago.webservice.service;

import com.albago.webservice.domain.Comments;
import com.albago.webservice.domain.CommentsRepository;
import com.albago.webservice.dto.posts.CommentsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentsService {
    private CommentsRepository commentsRepository;

    @Transactional
    public Long save(CommentsSaveRequestDto dto) {
        return commentsRepository.save(dto.toEntity()).getComment_id();
    }

    @Transactional
    public Comments findOne(Long post_id) {
        return commentsRepository.findOne(post_id);
    }

    @Transactional
    public List<Comments> findComments(Long post_id) {
        return commentsRepository.findComments(post_id);
    }

    @Transactional
    public String deleteComment(Long comment_id) {
        commentsRepository.delete(comment_id);
        return "delete";
    }

    @Transactional
    public String deleteCommentByPostId(Long post_id) {
        commentsRepository.deleteCommentsByPostId(post_id);
        return "delete";
    }

    @Transactional
    public String findFavor(Long comment_id) {
        return commentsRepository.findFavor(comment_id);
    }

    @Transactional
    public String findHate(Long comment_id) {
        return commentsRepository.findHate(comment_id);
    }

    @Transactional
    public int updateFavor(int favor, Long comment_id) {
        commentsRepository.updateFavor(favor, comment_id);
        return favor;
    }

    @Transactional
    public int updateHate(int hate, Long comment_id) {
        commentsRepository.updateHate(hate, comment_id);
        return hate;
    }

    @Transactional
    public String findPwd(Long comment_id) {
        return commentsRepository.findPwd(comment_id);
    }
}
