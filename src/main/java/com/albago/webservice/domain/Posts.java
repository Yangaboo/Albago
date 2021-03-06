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
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long postId;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private int cat_id;

    private int favor;

    private int hate;

    private int visit;

    @Builder
    public Posts(String title, String content, String author, String pwd, int cat_id, int favor, int hate, int visit) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.pwd = pwd;
        this.cat_id = cat_id;
        this.favor = favor;
        this.hate = hate;
        this.visit = visit;
    }
}
/*
    ALL: 0,
    SERVE: 1,
    MANAGE: 2,
    PARK: 3,
    IT: 4,
    CLEAN: 5,
    EXPRESS: 6,
    OFFICE: 7,
    ETC: 8
*/