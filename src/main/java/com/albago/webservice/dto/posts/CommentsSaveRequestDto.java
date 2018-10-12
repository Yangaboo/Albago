package com.albago.webservice.dto.posts;

import com.albago.webservice.domain.Comments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsSaveRequestDto {
    private String content;
    private String author;
    private String pwd;
    private int favor;
    private int hate;
    private long post_id;

    @Builder
    public CommentsSaveRequestDto(String content, String author, String pwd, long post_id) {
        this.content = content;
        this.author = author;
        this.pwd = pwd;
        this.favor = 0;
        this.hate = 0;
        this.post_id = post_id;
    }

    public Comments toEntity() {
        return Comments.builder()
                .content(content)
                .author(author)
                .pwd(pwd)
                .favor(favor)
                .hate(hate)
                .post_id(post_id)
                .build();
    }
}
