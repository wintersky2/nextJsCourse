package com.example.nextJsCourse.domain.article.service;

import com.example.nextJsCourse.domain.article.entity.Article;
import com.example.nextJsCourse.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getList() {
        return this.articleRepository.findAll();
    }

    public Article getArticle(Long id) {
        Optional<Article> _article = this.articleRepository.findById(id);
        if (_article.isEmpty()) {
            return null;
        }
        return _article.get();
    }

    public void create(String subject, String content){
        Article article = Article.builder()
                .subject(subject)
                .content(content)
                .build();

        this.articleRepository.save(article);
    }
}
