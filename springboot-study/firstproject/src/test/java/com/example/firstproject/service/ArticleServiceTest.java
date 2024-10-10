package com.example.firstproject.service;

import com.example.firstproject.models.dto.ArticleForm;
import com.example.firstproject.models.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired  // 스프링에 의해 관리되는 빈(Bean)에 객체를 주입받음(bean에서 내부적으로 new ArticleService()를 호출)
    ArticleService articleService;

    @Test
    void index() {
        // 1. 수동으로 예상 데이터 작성하기
        // 1-1. Article 객체 생성
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));
        // 2. 실제 데이터를 조회하는 메소드 실행
        List<Article> articles = articleService.index();
        // 3. 예상 데이터와 실제 데이터를 비교 및 검증
        assertEquals(expected.toString(), articles.toString()); // assertEquals(예상값, 실제값);
        assertEquals(expected.size(), articles.size()); // assertEquals(예상값, 실제값);
    }


    @Test
    void show_성공_존재하는_id_입력() {
        // 1. 수동으로 예상 데이터 작성하기(첫번째 id)
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        // 2. 실제 데이터를 조회하는 메소드 실행
        Article article = articleService.show(id);  //  id 1L의 article객체를 조회
        // 3. 예상 데이터와 실제 데이터를 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지않는_id_입력() {
        // 1. 수동으로 예상 데이터 작성하기(없는 아이디 -1)
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터를 조회하는 메소드 실행
        Article article = articleService.show(id);  //  id -1L의 article객체를 조회
        // 3. 예상 데이터와 실제 데이터를 비교 및 검증
        assertEquals(expected, article);    // assertEquals(예상값, 실제값);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력() {
        // 1. 수동으로 예상 데이터 작성하기
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);

        Article expected = new Article(4L, "라라라라", "4444");
        // 2. 실제 데이터를 조회하는 메소드 실행
        Article article = articleService.create(dto);
        // 3. 예상 데이터와 실제 데이터를 비교 및 검증
        assertEquals(expected.toString(), article.toString());    // assertEquals(예상값, 실제값);
    }

    @Test
    void create_실패_id가_포함된_dto_입력() {
        // 1. 수동으로 예상 데이터 작성하기
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 2. 실제 데이터를 조회하는 메소드 실행
        Article article = articleService.create(dto);
        // 3. 예상 데이터와 실제 데이터를 비교 및 검증
        assertEquals(expected, article);    // assertEquals(예상값, 실제값);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
        // 1. 수동으로 예상 데이터 작성하기
        Long id = 1L;
        String title = "하하하하";
        String content = "7777";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        // 2. 실제 데이터를 조회하는 메소드 실행
        Article article = articleService.update(id, dto);
        // 3. 예상 데이터와 실제 데이터를 비교 및 검증
        assertEquals(expected.toString(), article.toString());    // assertEquals(예상값, 실제값);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        // 1. 수동으로 예상 데이터 작성하기
        Long id = 1L;
        String title = "하하하하";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, "1111");
        // 2. 실제 데이터를 조회하는 메소드 실행
        Article article = articleService.update(id, dto);
        // 3. 예상 데이터와 실제 데이터를 비교 및 검증
        assertEquals(expected.toString(), article.toString());    // assertEquals(예상값, 실제값);
    }

    @Test
    @Transactional
    void update_실패_존재하지_않는_id의_dto_입력() {
        // 1. 수동으로 예상 데이터 작성하기
        Long id = -1L;
        String title = "호호호호";
        String content = "777777";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 2. 실제 데이터를 조회하는 메소드 실행
        Article article = articleService.update(id, dto);
        // 3. 예상 데이터와 실제 데이터를 비교 및 검증
        assertEquals(expected, article);    // assertEquals(예상값, 실제값);
    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력() {
        // 1. 수동으로 예상 데이터 작성하기
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        // 2. 실제 데이터를 조회하는 메소드 실행
        Article article = articleService.delete(id);
        // 3. 예상 데이터와 실제 데이터를 비교 및 검증
        assertEquals(expected.toString(), article.toString());    // assertEquals(예상값, 실제값);
    }

    @Test
    @Transactional
    void delete_실패_존재하지_않는_id_입력() {
        // 1. 수동으로 예상 데이터 작성하기
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터를 조회하는 메소드 실행
        Article article = articleService.delete(id);
        // 3. 예상 데이터와 실제 데이터를 비교 및 검증
        assertEquals(expected, article);    // assertEquals(예상값, 실제값);
    }
}