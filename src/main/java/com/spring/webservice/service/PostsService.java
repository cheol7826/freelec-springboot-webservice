package com.spring.webservice.service;

import com.spring.webservice.domain.posts.Posts;
import com.spring.webservice.domain.posts.PostsRepository;
import com.spring.webservice.web.dto.PostsResponseDto;
import com.spring.webservice.web.dto.PostsSaveRequestDto;
import com.spring.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        Posts savePost = postsRepository.save(requestDto.toEntity());

        return savePost.getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return posts.getId();
    }

    public PostsResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(posts);
    }

}
