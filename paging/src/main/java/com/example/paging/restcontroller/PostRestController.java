package com.example.paging.restcontroller;

import com.example.paging.Service.PostService;
import com.example.paging.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.web.PageableDefault;

// PostController를 @RestController로 변경
@RestController
public class PostRestController {

    @Autowired
    private PostService postService;

    @GetMapping("/api/posts")
    public Page<Post> getPosts(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.pageList(pageable);
    }
}

