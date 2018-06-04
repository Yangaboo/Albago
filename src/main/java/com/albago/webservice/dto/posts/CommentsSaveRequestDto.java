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

    @Builder
    public CommentsSaveRequestDto(String content, String author, String pwd, int favor, int hate) {
        this.content = content;
        this.author = author;
        this.pwd = pwd;
        this.favor = favor;
        this.hate = hate;
    }

    public Comments toEntity() {
        return Comments.builder()
                .content(content)
                .author(author)
                .pwd(pwd)
                .favor(favor)
                .hate(hate)
                .build();
    }
}
