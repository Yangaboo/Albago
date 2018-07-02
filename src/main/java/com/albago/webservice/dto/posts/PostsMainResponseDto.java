package com.albago.webservice.dto.posts;


import com.albago.webservice.domain.Posts;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.time.LocalDateTime;

@Getter
public class PostsMainResponseDto {
    private Long post_id;
    private String title;
    private String author;
    private String cat_id;
    private int favor;
    private int hate;

    public PostsMainResponseDto(Posts entity) {
        post_id = entity.getPost_id();
        title = entity.getTitle();
        author = entity.getAuthor();
        cat_id = entity.getCat_id();
        favor = entity.getFavor();
        hate = entity.getHate();
    }

    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

}

