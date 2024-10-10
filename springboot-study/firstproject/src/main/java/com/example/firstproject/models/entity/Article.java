package com.example.firstproject.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Article {
    @Id // 기본키(엔티티의 대표값 지정)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성 추가
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
//    @Column
//    private String author;

    public void patch(Article article) {
        if(!Objects.isNull(article)) {
            if(article.title != null) {
                this.title = article.title;
            }
            if(article.content != null) {
                this.content = article.content;
            }
        }
    }
}
