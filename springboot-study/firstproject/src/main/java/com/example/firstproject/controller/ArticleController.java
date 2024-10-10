package com.example.firstproject.controller;

import com.example.firstproject.dao.mapper.ArticleMapper;
import com.example.firstproject.models.dto.ArticleForm;
import com.example.firstproject.models.dto.ReplyDto;
import com.example.firstproject.models.entity.Article;
import com.example.firstproject.dao.repository.ArticleRepository;
import com.example.firstproject.models.vo.ArticleVO;
import com.example.firstproject.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired  //  스프링부트가 미리 생성해 놓은 레파지토리 개체 주입(DI(Dependency Injection))
    private ArticleRepository articleRepository;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("/articles/new")    //  브라우저에서 "/articles/new"로 요청이 들어오면 아래 메소드가 실행됨
    public String newArticleForm() {
        return "articles/new";  // templates/articles/new.mustache로 이동
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { //  ArticleForm은 DTO 성격을 가진 class
        //System.out.println(form);
        // log level단계: trace, debug, info, error, warn
        log.info(form.toString()); // 로깅을 위한 코드

        // 1. DTO(ArticleForm)를 엔티티(Article)로 변환
        Article article = form.toEntity();
        log.info(article.toString()); // 로깅을 위한 코드
        //System.out.println(article);

        // 2. 레파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString()); //  로깅을 위한 코드
        //System.out.println(saved);
        // return "redirect:/articles";    // 브라우저에서 /articles를 입력한 거와 같은 효과

        // 3. 입력페이지로 리다이렉트 하기
        return "redirect:/articles/" + saved.getId();
    }
    
    @GetMapping("/articles/{id}")   //  상세페이지 데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model) { //  매개변수로 id 받아오기
        log.info("id = " + id); // 로깅을 위한 코드
        // 1. id를 조회해 데이터 가져오기(만약에 id값이 없으면 null 반환)
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 해당 id게시글에 대한 댓글 목록 가져오기
        List<ReplyDto> replyDtoList = replyService.replys(id);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtoList", replyDtoList);
        // 3 뷰 페이지 반환하기
        return "articles/show"; // templates/articles/show.mustache로 이동
    }

    // 게시글 리스트 조회하기
    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 데이터 조회하기(list)
        // 1-1 Repository이용(JPA)
        // List<Article> articleList = (List<Article>) articleRepository.findAll();
        // 1-2 Mapper이용(MyBatis or iBatis)
        List<ArticleVO> articleVOList = articleMapper.getArticleList();
        List<Article> articleList = new ArrayList<>();
        for(ArticleVO articleVO : articleVOList) {
            articleList.add(articleVO.toEntity());
        }
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleList);
        // 3. 뷰 페이지 반환(설정)하기
        return "articles/index";    //  templates/articles/index.mustache로 이동
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 1. 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        // 1. DTO(ArticleForm)를 엔티티(Article)로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString()); // 로깅을 위한 코드
        // 2. 레파지터리로 엔티티를 DB에 저장
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값을 갱신하기
        if(target != null) {
            articleRepository.save(articleEntity); // 엔티티를 DB에 저장(갱신)
        }
        // 3. 수정결과 페이지로 리다이렉트 하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다.");
        // 1. 삭제할 대상 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        log.info(articleEntity.toString());
        // 2. 삭제 대상 실제 삭제하기
        if(articleEntity != null) {
            articleRepository.delete(articleEntity);
            rttr.addFlashAttribute("msg", "삭제되었습니다!!!");
        }
//        if(Objects.isNull(articleEntity){
//            articleRepository.delete(articleEntity);
//        }
        // 3. 삭제 결과 페이지로 리다이렉트하기(리스트 조회)
        return "redirect:/articles";
    }
}
