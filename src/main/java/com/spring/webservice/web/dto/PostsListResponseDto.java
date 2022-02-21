package com.spring.webservice.web.dto;

import com.spring.webservice.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts posts) {
        id = posts.getId();
        title = posts.getTitle();
        author = posts.getAuthor();
        content = posts.getContent();
        modifiedDate = posts.getModifiedDate();
    }
}
