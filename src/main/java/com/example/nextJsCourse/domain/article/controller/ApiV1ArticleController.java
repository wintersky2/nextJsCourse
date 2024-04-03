package com.example.nextJsCourse.domain.article.controller;

import com.example.nextJsCourse.domain.article.entity.Article;
import com.example.nextJsCourse.domain.article.service.ArticleService;
import com.example.nextJsCourse.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @AllArgsConstructor
    @Getter
    public static class ArticlesResponse {
        private final List<Article> articles;
    }


    @AllArgsConstructor
    @Getter
    public static class ArticleResponse {
        private final Article article;
    }

    @AllArgsConstructor
    @Getter
    public static class CreateArticleRequest {
        private final String subject;
        private final String content;
    }

    @AllArgsConstructor
    @Getter
    public static class CreateArticleResponse {
        private final Article article;
    }

    @AllArgsConstructor
    @NoArgsConstructor(force = true) //역직렬화 데이터를 원래 객체의 형태로 변환하는 것 ????
    @Getter
    public static class UpdateArticleRequest {
        private final String subject;
        private final String content;
    }

    @AllArgsConstructor
    @Getter
    public static class UpdateArticleResponse {
        private final Article article;
    }

    @GetMapping("")
    public RsData<ArticlesResponse> getArticles() {
        List<Article> articles = this.articleService.getList();

        return RsData.of("S-1", "성공", new ArticlesResponse(articles));
    }

    @GetMapping("/{id}")
    public RsData<ArticleResponse> getArticle(@PathVariable(value = "id") Long id) {
        return
                this.articleService.getArticle(id).map(article -> RsData.of(
                        "S-1",
                        "성공",
                        new ArticleResponse(article)
                )).orElseGet(() -> RsData.of(
                        "F-1",
                        "%d 번 게시물은 존재하지 않습니다.".formatted(id),
                        null
                ));
    }

    @PostMapping("")
    public RsData<CreateArticleResponse> postArticle(@Valid @RequestBody CreateArticleRequest createArticleRequest) {
        Article article = this.articleService.create(createArticleRequest.getSubject(), createArticleRequest.getContent());

        return RsData.of("S-1", "성공", new CreateArticleResponse(article));
    }

    @PutMapping("/{id}")
    public RsData<UpdateArticleResponse> putArticle(@Valid @RequestBody UpdateArticleRequest updateArticleRequest,
                                                    @PathVariable(value = "id") Long id) {
        Article article = this.articleService.update(id, updateArticleRequest.getSubject(), updateArticleRequest.getContent());

        if (article == null) {
            return RsData.of("F-1", "실패", null);
        }
        return RsData.of("S-1", "성공", new UpdateArticleResponse(article));
    }

    @DeleteMapping("/{id}")
    public RsData<String> deleteArticle(@PathVariable(value = "id") Long id) {
        return this.articleService.delete(id);
    }
}