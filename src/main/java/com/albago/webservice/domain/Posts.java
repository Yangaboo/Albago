package com.albago.webservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Posts {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false, name = "title")
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false, name = "content")
    private String content;

    @Column(length = 500, nullable = false, name = "author")
    private String author;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(columnDefinition = "INT", name = "like")
    private int like;

    @Column(columnDefinition = "INT", name = "unlike")
    private int unlike;

    @Builder
    public Posts (String title, String content, String author, String password, int like, int unlike) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
        this.like = like;
        this.unlike = unlike;
    }
}