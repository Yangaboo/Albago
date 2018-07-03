package com.albago.webservice.dto.posts;

import com.albago.webservice.domain.Posts;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;
    private String pwd;
    private int cat_id;
    private int favor;
    private int hate;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String pwd, int cat_id, int favor, int hate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.pwd = pwd;
        this.cat_id = cat_id;
        this.favor = favor;
        this.hate = hate;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .pwd(pwd)
                .cat_id(cat_id)
                .favor(favor)
                .hate(hate)
                .build();
    }
}
