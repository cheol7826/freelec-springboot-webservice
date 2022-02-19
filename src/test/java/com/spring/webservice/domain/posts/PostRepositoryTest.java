package com.spring.webservice.domain.posts;

import com.spring.webservice.web.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    void cleanUp(){
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 저장 및 불러오기")
    void saveAndGetPosts(){
        String title = "Test title";
        String content = "Test content";
        String author = "author";

        Posts posts = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        postsRepository.save(posts);

        List<Posts> postsList = postsRepository.findAll();

        Posts getPosts = postsList.get(0);
        assertThat(getPosts.getTitle()).isEqualTo(title);
        assertThat(getPosts.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("생성, 수정시간 확인")
    void baseTimeEntity(){
        LocalDateTime now = LocalDateTime.of(2022, 2, 19, 16, 25);
        postsRepository.save(Posts.builder()
                .title("test title")
                .content("test content")
                .author("test author")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
