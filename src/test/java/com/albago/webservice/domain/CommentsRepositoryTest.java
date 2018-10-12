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
        .content("asd")
        .author("asd")
        .pwd("asd")
        .favor(3)
        .hate(2)
        .post_id(1)
        .build());

        // when
        List<Comments> commentsList = commentsRepository.findAll();

        // then
        Comments comments = commentsList.get(0);
        assertThat(comments.getContent(), is("asd"));
        assertThat(comments.getAuthor(), is("asd"));
        assertThat(comments.getPwd(), is("asd"));
        assertThat(comments.getFavor(), is(3));
        assertThat(comments.getHate(), is(2));
        assertThat(comments.getPost_id(), is(1L));
    }

    @Test
    public void BaseTimeEntity_post() {
        // given
        LocalDateTime now = LocalDateTime.now();
        commentsRepository.save(Comments.builder()
            .content("asd")
            .author("asd")
            .pwd("asd")
            .favor(3)
            .hate(2)
            .post_id(1)
            .build());

        // when
        List<Comments> commentsList = commentsRepository.findAll();

        // then
        Comments comments = commentsList.get(0);
        assertTrue(comments.getCreatedDate().isAfter(now));
    }
}
