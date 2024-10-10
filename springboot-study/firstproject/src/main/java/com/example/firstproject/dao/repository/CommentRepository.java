//package com.example.firstproject.repository;
//
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface CommentRepository extends JpaRepository<Comment, Long> {
//    // 특정 게시글의 모든 댓글 조회
//    // jpa를 이용하지 않고 순수 SQL쿼리로 실행
//    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
//    List<Comment> findByArticleId(@Param(value = "articleId") Long articleId); // 특정 게시글의 모든 댓글 조회
//
//    //특정 닉네임의 모든 댓글 조회(다른 예로 xml로 작성도 가능) '->'resources/META-INF/orm.xml라는 파일로 세팅
//    List<Comment> findByNickname(@Param(value = "nickname")String nickname); // 특정 닉네임의 모든 댓글 조회
//
//}
