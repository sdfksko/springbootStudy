package com.example.paging.restcontroller;

import com.example.paging.entity.Post;
import com.example.paging.repository.PostRepository;
import com.example.paging.restservice.PostRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// PostController를 @RestController로 변경
@RestController
public class PostRestController {

    @Autowired
    private PostRestService postRestService;


    @GetMapping("/api/posts")
    public Page<Post> getPosts(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Post> postList = postRestService.pageList(pageable);
        return postList;
    }


}

