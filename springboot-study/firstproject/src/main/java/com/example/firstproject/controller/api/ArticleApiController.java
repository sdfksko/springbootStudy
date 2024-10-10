package com.example.firstproject.controller.api;

import com.example.firstproject.models.dto.ArticleForm;
import com.example.firstproject.models.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired  // 스프링부트가 미리 생성해 놓은 서비스 객체 주입(DI(Dependency Injection))
    private ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm formDto) {
        Article createdArticle =  articleService.create(formDto);

        return  createdArticle != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(createdArticle)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(
            @PathVariable Long id,
            @RequestBody ArticleForm dto
    ) {
        Article updatedArticle =  articleService.update(id, dto);

        return  updatedArticle != null
                ? ResponseEntity.status(HttpStatus.OK).body(updatedArticle)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deletedArticle = articleService.delete(id);

        return deletedArticle != null
                ? ResponseEntity.status(HttpStatus.OK).body(deletedArticle)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/api/transaction-test")   // 트랜잭션 테스트용 API(여러 게시글 생성 요청 접수)
    public ResponseEntity<List<Article>> transactionTest(
            @RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);

        return  createdList != null
                ? ResponseEntity.status(HttpStatus.OK).body(createdList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

//    @Autowired  //  스프링부트가 미리 생성해 놓은 레파지토리 개체 주입(DI(Dependency Injection))
//    private ArticleRepository articleRepository;
//
//    // 게시글 목록보기 API(GET)
//    @GetMapping("/api/articles")
//    public List<Article> index() {
//        log.debug("api/articles 실행됨");
//        return articleRepository.findAll();
//    }
//
//    // 게시글 상세조회 API(GET)
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id) {
//        log.debug("api/articles" + id + "실행됨");
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    // 게시글 등록 API(POST)
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm form) {
//        log.debug(form.toString());
//        Article article = form.toEntity();
//        return articleRepository.save(article);
//    }
//
//    // 4. 데이터 전체 혹은 일부 수정하기
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(
//            @PathVariable Long id,
//            @RequestBody ArticleForm dto
//    ) {
//        // 1. DTO(ArticleForm)를 엔티티(Article)로 변환
//        Article articleEntity = dto.toEntity();
//        log.info("id: {}. article: {}", id, articleEntity.toString());
//
//        // 2. 타깃 조회하기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3. 타깃의 값을 체크해서 잘못된 요청 처리하기
//        if(target == null || id != articleEntity.getId()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 4. 타깃의 값을 실제 변경하기
//        target.patch(articleEntity);
//        Article updated = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    // 5. 데이터 삭제하기
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        // 1. 타깃 조회하기
//        Article target = articleRepository.findById(id).orElse(null);
//        // 2. 타깃이 없으면 400(BAD_REQUEST) 응답하거나 잘못된 요청을 처리하기
//        if(target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 3. 대상 삭제하기(타깃이 있으면 삭제하고 200응답하기)
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).body(target);
//    }
}
