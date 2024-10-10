//package com.example.firstproject.service;
//
//import com.example.firstproject.dto.CommentDto;
//import com.example.firstproject.entity.Article;
//import com.example.firstproject.entity.Comment;
//import com.example.firstproject.repository.ArticleRepository;
//import com.example.firstproject.repository.CommentRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Service
//public class CommentService {
//    @Autowired
//    private CommentRepository commentRepository;
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    public List<CommentDto> comments(Long articleId) {
//    /*    // 1. article아이디에 해당하는 모든 댓글을 조회
//        List<Comment> commentList = commentRepository.findByArticleId(articleId);
//        // 2. Entity -> DTO로 변환(Comment -> CommentDto)
//        List<CommentDto> dtos = new ArrayList<>();
//        for(int i = 0; i < commentList.size(); i++) {
//            Comment comment = commentList.get(i);
////            CommentDto dto = new CommentDto(
////                    comment.getId(),
////                    comment.getArticle().getId(),
////                    comment.getNickname(),
////                    comment.getBody()
////            );
//            CommentDto dto = CommentDto.createCommentDto(comment);
//            dtos.add(dto);
//        }*/
//        // 3. List<CommentDto> 결과로 반환
//        return commentRepository.findByArticleId(articleId) // 댓글 엔티티 목록 조회
//                .stream()   // 댓글 엔티티 목록을 스트림으로 변환
//                .map(comment -> CommentDto.createCommentDto(comment))   // 엔티티를 DTO로 매핑
//                // 스트림을 리스트로 변환
//                .collect(Collectors.toList()) // 스트림을 리스트로 변환
//                ;
//    }
//    // Ctrl + Shift + /
//
//    // 2. 댓글 생성
//    public CommentDto create(Long articleId, CommentDto commentDto) {
//        Article article = articleRepository.findById(articleId).orElseThrow(
//                () -> new IllegalArgumentException("댓글 생성 실패! 게시글의 번호가 없습니다")
//        );
//        log.info(article.toString());
//        log.info(commentDto.toString());
//        Comment comment = Comment.createComment(commentDto, article);
//        Comment created = commentRepository.save(comment);
//
//        return CommentDto.createCommentDto(created);
//    }
//
//    // 3. 댓글 수정
//    public CommentDto update(Long id, CommentDto commentDto) {
//        // 1. 댓글 조회 및 존재 여부 확인 -> 없으면 예외 발생
//        Comment target = commentRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다.")
//        );
//        // 2. 실제 댓글 수정
//        target.patch(commentDto);
//
//        // 3. 수정한 댓글을 DB에 저장
//        Comment updated = commentRepository.save(target);
//        // 4. DB에 저장한 엔티티를 DTO로 변환해 반환
//        return CommentDto.createCommentDto(updated);
//    }
//
//    // 4. 댓글 삭제
//    @Transactional
//    public CommentDto delete(Long id) {
//        Comment target = commentRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("댓글 삭제 실패! 대상 댓글이 존재하지 않습니다.")
//        );
//        commentRepository.delete(target);
//        return CommentDto.createCommentDto(target);
//    }
//}
