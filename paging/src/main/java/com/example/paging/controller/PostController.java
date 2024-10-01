package com.example.paging.controller;

import com.example.paging.Service.PostService;
import com.example.paging.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public String post(Model model,
                       @RequestParam(value = "page", defaultValue = "1") int page,
                       @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {


        Pageable newPageable = PageRequest.of(page - 1, pageable.getPageSize(), pageable.getSort());

        Page<Post> list = postService.pageList(newPageable);

        int totalPages = list.getTotalPages();
        int currentPage = newPageable.getPageNumber() + 1;


        int start = Math.max(1, currentPage - 2);
        int end = Math.min(totalPages, currentPage + 2);

        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            pageNumbers.add(i);
        }

        int firstPage = 1;
        int lastPage = totalPages;

        model.addAttribute("posts", list);
        model.addAttribute("prev", Math.max(1, currentPage - 1));
        model.addAttribute("next", Math.min(totalPages, currentPage + 1));
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("lastPage", lastPage);

        return "post"; // Mustache 템플릿 이름
    }

    @GetMapping("/newPost")
    public String newPost() {
      return "newPost";
    }

    @GetMapping("/restPost")
    public ModelAndView restPost() {
        return new ModelAndView("restPost");
    }
}
