package com.albago.webservice.dto.posts;

import com.albago.webservice.domain.Posts;
import lombok.Builder;
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
    private int visit;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String pwd, int cat_id, int visit) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.pwd = pwd;
        this.cat_id = cat_id;
        this.favor = 0;
        this.hate = 0;
        this.visit = visit;
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
                .visit(visit)
                .build();
    }
}
