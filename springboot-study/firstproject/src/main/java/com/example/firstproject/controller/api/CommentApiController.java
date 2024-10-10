//package com.example.firstproject.controller.api;
//
//import com.example.firstproject.dto.ArticleForm;
//import com.example.firstproject.dto.CommentDto;
//import com.example.firstproject.entity.Article;
//import com.example.firstproject.entity.Comment;
//import com.example.firstproject.service.CommentService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//public class CommentApiController {
//    @Autowired
//    private CommentService commentService;
//
//    // 1. 댓글 조회
//    @GetMapping("/api/articles/{articleId}/comments")
//    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
//        // service(서비스)파일에 로직을 위임하여 결과 응답 받은 걸 세팅
//        List<CommentDto> commentDtoList = commentService.comments(articleId);
//        return ResponseEntity.status(HttpStatus.OK).body(commentDtoList);
//    }
//
//    // 2. 댓글 생성
//    @PostMapping("/api/articles/{articleId}/comments")
//    public ResponseEntity<CommentDto> create(
//            @PathVariable Long articleId,
//            @RequestBody CommentDto commentDto) {
//        // service(서비스)파일에 로직을 위임하여 결과 응답 받은 걸 세팅
//        CommentDto createDto = commentService.create(articleId, commentDto);
//
//        return ResponseEntity.status(HttpStatus.OK).body(createDto);
//    }
//
//    // 3. 댓글 수정
//    @PatchMapping("/api/comments/{id}")
//    public ResponseEntity<CommentDto> update(
//            @PathVariable Long id,
//            @RequestBody CommentDto commentDto
//    ) {
//        // service(서비스)파일에 로직을 위임하여 결과 응답 받은 걸 세팅
//        CommentDto updateDto = commentService.update(id, commentDto);
//
//        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
//    }
//
//    // 4. 댓글 삭제
//    @DeleteMapping("/api/comments/{id}")
//    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
//
//        CommentDto deleteDto = commentService.delete(id);
//        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
//    }
//}
