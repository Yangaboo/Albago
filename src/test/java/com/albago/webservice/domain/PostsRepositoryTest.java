package com.albago.webservice.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void save_load_content() {
        // given
        postsRepository.save(Posts.builder()
        .title("test")
        .content("test")
        .author("test")
        .pwd("test")
        .favor(1)
        .hate(1)
        .cat_id(1)
        .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("test"));
        assertThat(posts.getContent(), is("test"));
        assertThat(posts.getAuthor(), is("test"));
        assertThat(posts.getPwd(), is("test"));
//        assertThat(posts.getFavor(), is(1));
//        assertThat(posts.getHate(), is(1));
        assertThat(posts.getCat_id(), is(1));
    }

    @Test
    public void BaseTimeEntity_post () {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트")
                .content("테스트")
                .author("너너너")
                .pwd("1234")
                .cat_id(1)
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
    }
}
