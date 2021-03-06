package com.albago.webservice.service;

import com.albago.webservice.Post;
import com.albago.webservice.domain.Posts;
import com.albago.webservice.domain.PostsRepository;
import com.albago.webservice.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Page<Posts> findPostsByCatId(int catId, Pageable pageable) {
        return postsRepository.findPostsByCat_id(catId, pageable);
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

    @Transactional
    public String findVisit(Long post_id) {
        return postsRepository.findVisit(post_id);
    }

    @Transactional
    public Page<Posts> findPostsByTitle(String title, Pageable pageable) {
        return postsRepository.findPostsByTitle(title, pageable);
    }

    @Transactional
    public Page<Posts> findPostsByContent(String content, Pageable pageable) {
        return postsRepository.findPostsByContent(content, pageable);
    }

    @Transactional
    public int updateVisit(int visit, Long post_id) {
        postsRepository.updateVisit(visit, post_id);
        return visit;
    }

    @Transactional
    public int updatePostContent(String content, Long post_id) {
        postsRepository.updatePostContent(content, post_id);
        return 1;
    }

    @Transactional
    public int updatePostTitle(String title, Long post_id) {
        postsRepository.updatePostTitle(title, post_id);
        return 1;
    }
}