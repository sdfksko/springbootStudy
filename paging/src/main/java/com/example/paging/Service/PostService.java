package com.example.paging.Service;

import com.example.paging.entity.Post;
import com.example.paging.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Page<Post> pageList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
