package com.example.firstproject.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
//@Table(name = "member")   // oracle 사용시에 user라는 이름은 이미 사용하기 때문에 테이블 이름을 member로 교체
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;        // ROLE_USER, ROLE_MANAGER, ROLE_ADMIN

    private String provider;  // GOOGLE, NAVER, KAKAO
    private String providerId;  //  각각의 provider사의 id(google -> sub, naver -> ?, kakao -> ?)
    @CreationTimestamp
    private Timestamp createDate;

}
