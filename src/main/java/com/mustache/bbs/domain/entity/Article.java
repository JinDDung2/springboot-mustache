package com.mustache.bbs.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTICLES_ID")
    private Long id;

    private String title;
    private String content;

    public Article(String title, String contents) {
        this.title = title;
        this.content = contents;
    }
}
