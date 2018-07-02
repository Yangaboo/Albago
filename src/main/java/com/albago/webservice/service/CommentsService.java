package com.albago.webservice.service;

import com.albago.webservice.domain.CommentsRepository;
import com.albago.webservice.dto.posts.CommentsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class CommentsService {
    private CommentsRepository commentsRepository;

    @Transactional
    public Long save(CommentsSaveRequestDto dto) {
        return commentsRepository.save(dto.toEntity()).getComment_id();
    }
}
