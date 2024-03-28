package com.example.nextJsCourse.domain.article.controller;

import com.example.nextJsCourse.domain.article.entity.Article;
import com.example.nextJsCourse.domain.article.service.ArticleService;
import com.example.nextJsCourse.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("")
    public RsData<List<Article>> getArticles() {
        List<Article> articles = this.articleService.getList();
//        articles.add(new Article((1L)));
//        articles.add(new Article((2L)));
//        articles.add(new Article((3L)));

        return RsData.of("S-1", "성공", articles);
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable("id") Long id) {
        Article article = this.articleService.getArticle(id);

        return article;
    }
}