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

    @Column(columnDefinition = "INT", name = "favor")
    private int favor;

    @Column(columnDefinition = "INT", name = "hate")
    private int hate;

    @Builder
    public Comments(String content, String author, String pwd, int favor, int hate ) {
        this.author = author;
        this.content = content;
        this.pwd = pwd;
        this.favor = favor;
        this.hate = hate;
    }
}