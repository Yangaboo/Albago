package com.albago.webservice.dto.posts;

import com.albago.webservice.domain.Comments;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CommentsMainResponseDto {
    private Long comment_id;
    private String content;
    private String author;
    private String pwd;
    private int favor;
    private int hate;
    private Long post_id;

    public CommentsMainResponseDto(Comments entity) {
        comment_id = entity.getComment_id();
        content = entity.getContent();
        author = entity.getAuthor();
        pwd = entity.getPwd();
        favor = entity.getFavor();
        hate = entity.getHate();
        post_id = entity.getPost_id();
    }

    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
