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
public class Job {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int pay;

    @Column(nullable = false)
    private int work_date;

    @Column(nullable = false)
    private boolean night;

    @Column(nullable = false)
    private boolean weekend;

    @Column(nullable = false)
    private int age;

    @Builder
    public Job(String title, int pay, int work_date, boolean night, boolean weekend, int age) {
        this.title = title;
        this.pay = pay;
        this.work_date = work_date;
        this.night = night;
        this.weekend = weekend;
        this.age = age;
    }
}
