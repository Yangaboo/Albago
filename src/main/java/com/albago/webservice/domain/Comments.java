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
public class Comments {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false, name = "content")
    private String content;

    @Column(length = 500, nullable = false, name = "author")
    private String author;

    @Column(nullable = false, name = "pwd")
    private String pwd;

    @Column(columnDefinition = "INT", name = "like")
    private int like;

    @Column(columnDefinition = "INT", name = "unlike")
    private int unlike;

    @Builder
    public Comments(String content, String author, String pwd, int like, int unlike ) {
        this.author = author;
        this.content = content;
        this.pwd = pwd;
        this.like = like;
        this.unlike = unlike;
    }
}