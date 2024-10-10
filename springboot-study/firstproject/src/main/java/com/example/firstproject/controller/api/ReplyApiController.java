package com.example.firstproject.controller.api;

import com.example.firstproject.models.dto.ReplyDto;
import com.example.firstproject.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ReplyApiController {
    @Autowired
    private ReplyService replyService;

    // 1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/replys")
    public ResponseEntity<List<ReplyDto>> replys(@PathVariable Long articleId) {
        // service(서비스)파일에 로직을 위임하여 결과 응답 받은 걸 세팅
        List<ReplyDto> replyDtoList = replyService.replys(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(replyDtoList);
    }

    // 2. 댓글 생성
    @PostMapping("/api/articles/{articleId}/replys")
    public ResponseEntity<ReplyDto> create(
            @PathVariable Long articleId,
            @RequestBody ReplyDto replyDto) {
        // service(서비스)파일에 로직을 위임하여 결과 응답 받은 걸 세팅
        ReplyDto createDto = replyService.create(articleId, replyDto);

        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    // 3. 댓글 수정
    @PatchMapping("/api/replys/{id}")
    public ResponseEntity<ReplyDto> update(
            @PathVariable Long id,
            @RequestBody ReplyDto replyDto
    ) {
        // service(서비스)파일에 로직을 위임하여 결과 응답 받은 걸 세팅
        ReplyDto updateDto = replyService.update(id, replyDto);

        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // 4. 댓글 삭제
    @DeleteMapping("/api/replys/{id}")
    public ResponseEntity<ReplyDto> delete(@PathVariable Long id) {

        ReplyDto deleteDto = replyService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }
}

