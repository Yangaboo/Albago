package com.albago.webservice.service;

import com.albago.webservice.domain.Posts;
import com.albago.webservice.domain.PostsRepository;
import com.albago.webservice.dto.posts.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void DTO_data_save_to_Posts() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title("테스트")
                .content("테스트")
                .author("테스트")
                .pwd("test")
                .cat_id(0)
                .favor(1)
                .hate(1)
                .build();

        // when
        postsService.save(dto);

        // then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getPwd()).isEqualTo(dto.getPwd());
        assertThat(posts.getCat_id()).isEqualTo(dto.getCat_id());
        assertThat(posts.getFavor()).isEqualTo(dto.getFavor());
        assertThat(posts.getHate()).isEqualTo(dto.getHate());
    }
}
