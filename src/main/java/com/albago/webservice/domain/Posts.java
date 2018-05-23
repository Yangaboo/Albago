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

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String pwd;

    private int favor;

    private int hate;

    @Column(nullable = false)
    private int cat_id;

    @Builder
    public Posts(String title, String content, String author, String pwd, int favor, int hate, int cat_id) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.pwd = pwd;
        this.favor = favor;
        this.hate = hate;
        this.cat_id = cat_id;
    }
}