package com.albago.webservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comments extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long comment_id;

    @Column(columnDefinition = "TEXT", nullable = false, name = "content")
    private String content;

    @Column(length = 500, nullable = false, name = "author")
    private String author;

    @Column(nullable = false, name = "pwd")
    private String pwd;

    @Column(columnDefinition = "INT", name = "favor")
    private int favor;

    @Column(columnDefinition = "INT", name = "hate")
    private int hate;

    @JoinColumn(name = "post_id")
    @Column(columnDefinition = "LONG", name = "post_id")
    private long post_id;

    @Builder
    public Comments(String content, String author, String pwd, int favor, int hate, long post_id) {
        this.author = author;
        this.content = content;
        this.pwd = pwd;
        this.favor = favor;
        this.hate = hate;
        this.post_id = post_id;
    }
}