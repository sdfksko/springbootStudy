//package com.example.firstproject.dto;
//
//import com.example.firstproject.entity.Article;
//import com.example.firstproject.entity.Comment;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@ToString
//public class CommentDto {
//    private Long id;    // 댓글 번호
//    private Long articleId;  // 댓글이 달린 게시글(부모) 번호
//    private String nickname;    // 댓글 작성자 닉네임
//    private String body;    //  댓글 본문
//
//    public static CommentDto createCommentDto(Comment comment) {
//        return new CommentDto(
//                comment.getId(),    // 댓글 실제 번호
//                comment.getArticle().getId(),   // 댓글 엔티티의 부모 게시글 id
//                comment.getNickname(),  //  댓글 작성자 닉네임
//                comment.getBody()   //  댓글 본문
//        );
//    }
//}
