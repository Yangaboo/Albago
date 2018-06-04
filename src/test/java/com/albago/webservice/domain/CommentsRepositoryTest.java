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
public class CommentsRepositoryTest {

    @Autowired
    CommentsRepository commentsRepository;

    @After
    public void cleanup() {
        commentsRepository.deleteAll();
    }

    @Test
    public void save_load_content() {
        // given
        commentsRepository.save(Comments.builder()
        .content("test")
        .author("test")
        .pwd("1234")
        .favor(1)
        .hate(2)
        .build());

        // when
        List<Comments> commentsList = commentsRepository.findAll();

        // then
        Comments comments = commentsList.get(0);
        assertThat(comments.getContent(), is("test"));
        assertThat(comments.getAuthor(), is("test"));
        assertThat(comments.getPwd(), is("1234"));
        assertThat(comments.getFavor(), is(1));
        assertThat(comments.getHate(), is(2));
    }

    @Test
    public void BaseTimeEntity_post() {
        // given
        LocalDateTime now = LocalDateTime.now();
        commentsRepository.save(Comments.builder()
            .content("asd")
            .author("asd")
            .pwd("asd")
            .favor(1)
            .hate(1)
            .build());

        // when
        List<Comments> commentsList = commentsRepository.findAll();

        // then
        Comments comments = commentsList.get(0);
        assertTrue(comments.getCreatedDate().isAfter(now));
    }
}
