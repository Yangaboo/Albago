package com.albago.webservice.service;

import com.albago.webservice.domain.Comments;
import com.albago.webservice.domain.CommentsRepository;
import com.albago.webservice.dto.posts.CommentsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentsServiceTest {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsRepository commentsRepository;

    @After
    public void cleanup() {
        commentsRepository.deleteAll();
    }

    @Test
    public void DTO_data_save_to_Comments() {
        // given
        CommentsSaveRequestDto dto = CommentsSaveRequestDto.builder()
                .content("asd")
                .author("asd")
                .pwd("sad")
                .favor(1)
                .hate(1)
                .build();

        // when
        commentsService.save(dto);

        // then
        Comments comments = commentsRepository.findAll().get(0);
        assertThat(comments.getContent()).isEqualTo(dto.getContent());
        assertThat(comments.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(comments.getPwd()).isEqualTo(dto.getPwd());
        assertThat(comments.getFavor()).isEqualTo(dto.getFavor());
        assertThat(comments.getHate()).isEqualTo(dto.getHate());
    }
}
