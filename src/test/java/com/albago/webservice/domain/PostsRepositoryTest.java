package com.albago.webservice.domain;

import javafx.geometry.Pos;
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
        .title("테스트")
        .content("본문")
        .author("나")
        .password("1234")
        .build());

        // when
        List<Posts> postsList = PostsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트"));
        assertThat(posts.getContent(), is("본문"));
        assertThat(posts.getAuthor(), is("나"));
        assertThat(posts.getPassword(), is("1234"));
    }
}
