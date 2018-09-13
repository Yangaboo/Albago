package com.albago.webservice.service;

import com.albago.webservice.domain.Posts;
import com.albago.webservice.domain.PostsRepository;
import com.albago.webservice.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getPostId();
    }

    @Transactional(readOnly = true)
    public Page<Posts> findAll(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }

    @Transactional
    public String deletePost(Long post_id) {
        postsRepository.delete(post_id);
        return "delete";
    }

    @Transactional
    public Posts findOne(Long post_id) {
        return postsRepository.findOne(post_id);
    }
}