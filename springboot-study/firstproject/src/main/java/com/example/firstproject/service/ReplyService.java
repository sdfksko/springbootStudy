package com.example.firstproject.service;

import com.example.firstproject.models.dto.ReplyDto;
import com.example.firstproject.models.entity.Article;
import com.example.firstproject.models.entity.Reply;
import com.example.firstproject.dao.repository.ArticleRepository;
import com.example.firstproject.dao.repository.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<ReplyDto> replys(Long articleId) {
    /*    // 1. article아이디에 해당하는 모든 댓글을 조회
        List<Comment> commentList = commentRepository.findByArticleId(articleId);
        // 2. Entity -> DTO로 변환(Comment -> CommentDto)
        List<CommentDto> dtos = new ArrayList<>();
        for(int i = 0; i < commentList.size(); i++) {
            Comment comment = commentList.get(i);
//            CommentDto dto = new CommentDto(
//                    comment.getId(),
//                    comment.getArticle().getId(),
//                    comment.getNickname(),
//                    comment.getBody()
//            );
            CommentDto dto = CommentDto.createCommentDto(comment);
            dtos.add(dto);
        }*/
        // 3. List<CommentDto> 결과로 반환
        return replyRepository.findByArticleId(articleId) // 댓글 엔티티 목록 조회
                .stream()   // 댓글 엔티티 목록을 스트림으로 변환
                .map(reply -> ReplyDto.createReplyDto(reply))   // 엔티티를 DTO로 매핑
                // 스트림을 리스트로 변환
                .collect(Collectors.toList()) // 스트림을 리스트로 변환
                ;
    }
    // Ctrl + Shift + /

    // 2. 댓글 생성
    public ReplyDto create(Long articleId, ReplyDto replyDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new IllegalArgumentException("댓글 생성 실패! 게시글의 번호가 없습니다")
        );
        log.info(article.toString());
        log.info(replyDto.toString());
        Reply reply = Reply.createReply(replyDto, article);
        Reply created = replyRepository.save(reply);

        return ReplyDto.createReplyDto(created);
    }

    // 3. 댓글 수정
    public ReplyDto update(Long id, ReplyDto replyDto) {
        // 1. 댓글 조회 및 존재 여부 확인 -> 없으면 예외 발생
        Reply target = replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다.")
        );
        // 2. 실제 댓글 수정
        target.patch(replyDto);

        // 3. 수정한 댓글을 DB에 저장
        Reply updated = replyRepository.save(target);
        // 4. DB에 저장한 엔티티를 DTO로 변환해 반환
        return ReplyDto.createReplyDto(updated);
    }

    // 4. 댓글 삭제
    @Transactional
    public ReplyDto delete(Long id) {
        Reply target = replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글 삭제 실패! 대상 댓글이 존재하지 않습니다.")
        );
        replyRepository.delete(target);
        return ReplyDto.createReplyDto(target);
    }
}
