package com.albago.webservice.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
//        assertThat(posts.getLike(), is(1));
//        assertThat(posts.getHate(), is(1));
        assertThat(posts.getCat_id(), is(1));
    }
}
