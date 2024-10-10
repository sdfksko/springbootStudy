package com.example.secondproject.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Wish {
    @Id // 기본키(엔티티의 대표값 지정)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성 추가
    private Integer id;     // 위시리스트 번호

    private String title;           // 검색결과 제목
    private String category;        // 검색결과 카테고리
    private String jibunAddress;    // 검색결과 지번주소
    private String roadAddress;      // 검색결과 도로명주소
    private String homepageLink;    // 검색결과 홈페이지 주소
    private String imageLink;       // 검색결과 이미지 주소

    private boolean visitIs;        // 방문여부
    private int visitCount;         // 방문횟수
    private String lastVisitDate;     // 마지막 방문일자

    public boolean getVisitIs() {
        return this.visitIs;
    }
}
