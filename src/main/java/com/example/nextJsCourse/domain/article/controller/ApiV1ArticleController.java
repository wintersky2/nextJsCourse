package com.example.nextJsCourse.domain.article.controller;

import com.example.nextJsCourse.domain.article.entity.Article;
import com.example.nextJsCourse.domain.article.service.ArticleService;
import com.example.nextJsCourse.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @Data
    @AllArgsConstructor
    public static class ReadArticlesResponse {
        private final List<Article> articles;
    }


    @Data
    @AllArgsConstructor
    public static class ReadArticleResponse {
        private final Article article;
    }

    @Data
    public static class CreateArticleRequest {
        @NotBlank
        private String subject;
        @NotBlank
        private String content;
    }

    @Data
    @AllArgsConstructor
    public static class CreateArticleResponse {
        private final Article article;
    }

    @Data
    public static class UpdateArticleRequest {
        @NotBlank
        private String subject;
        @NotBlank
        private String content;
    }

    @Data
    @AllArgsConstructor
    public static class UpdateArticleResponse {
        private final Article article;
    }

    @GetMapping("")
    public RsData<ReadArticlesResponse> getArticles() {
        List<Article> articles = this.articleService.getList();

        return RsData.of("S-1", "성공", new ReadArticlesResponse(articles));
    }

    @GetMapping("/{id}")
    public RsData<ReadArticleResponse> getArticle(@PathVariable(value = "id") Long id) {
        return
                this.articleService.getArticle(id).map(article -> RsData.of(
                        "S-2",
                        "성공",
                        new ReadArticleResponse(article)
                )).orElseGet(() -> RsData.of(
                        "F-2",
                        "%d 번 게시물은 존재하지 않습니다.".formatted(id),
                        null
                ));
    }

    @PostMapping("")
    public RsData<CreateArticleResponse> postArticle(@Valid @RequestBody CreateArticleRequest createArticleRequest) {
        RsData<Article> postRs = this.articleService.create(createArticleRequest.getSubject(), createArticleRequest.getContent());

        if (postRs.isFail()) return (RsData) postRs;
        return RsData.of(
                postRs.getResultCode(),
                postRs.getMsg(),
                new CreateArticleResponse(postRs.getData()));
    }

    @PatchMapping("/{id}")
    public RsData<UpdateArticleResponse> putArticle(@Valid @RequestBody UpdateArticleRequest updateArticleRequest,
                                                    @PathVariable(value = "id") Long id) {
        Optional<Article> optionalArticle = this.articleService.findById(id);

        if (optionalArticle.isEmpty()) return RsData.of(
                "F-1",
                "%d번 게시물은 존재하지 않습니다.".formatted(id),
                null
        );

        // 회원 권한 체크 canModify();

        RsData<Article> modifyRs = this.articleService.update(optionalArticle.get(), updateArticleRequest.getSubject(), updateArticleRequest.getContent());

        return RsData.of(
                modifyRs.getResultCode(),
                modifyRs.getMsg(),
                new UpdateArticleResponse(modifyRs.getData())
        );
    }

    @DeleteMapping("/{id}")
    public RsData deleteArticle(@PathVariable(value = "id") Long id) {
        Optional<Article> optionalArticle = this.articleService.findById(id);

        if (optionalArticle.isEmpty()) return RsData.of(
                "F-1",
                "%d번 게시물은 존재하지 않습니다.".formatted(id),
                null
        );

        RsData deleteRs = this.articleService.delete(optionalArticle.get());
        return RsData.of(
                deleteRs.getResultCode(),
                deleteRs.getMsg(),
                deleteRs.getData());
    }
}