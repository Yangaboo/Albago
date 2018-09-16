package com.albago.webservice.service;

import com.albago.webservice.domain.Posts;
import com.albago.webservice.domain.PostsRepository;
import com.albago.webservice.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public String findPwd(Long post_id) {
        return postsRepository.findPwd(post_id);
    }

    @Transactional
    public String findFavor(Long post_id) {
        return postsRepository.findFavor(post_id);
    }

    @Transactional
    public String findHate(Long post_id) {
        return postsRepository.findHate(post_id);
    }

    @Transactional
    public Page<Posts> findPostsByCatId(int cat_id, Pageable pageable) {
        return postsRepository.findPostsByCat_id(cat_id, pageable);
    }

    @Transactional
    public int updateFavor(int favor, Long post_id) {
        postsRepository.updateFavor(favor, post_id);
        return favor;
    }

    @Transactional
    public int updateHate(int hate, Long post_id) {
        postsRepository.updateHate(hate, post_id);
        return hate;
    }

    @Transactional
    public Posts findOne(Long post_id) {
        return postsRepository.findOne(post_id);
    }
}