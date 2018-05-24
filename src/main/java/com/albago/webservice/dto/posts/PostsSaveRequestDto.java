package com.albago.webservice.dto.posts;

import com.albago.webservice.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
