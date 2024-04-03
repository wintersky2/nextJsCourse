package com.example.nextJsCourse.domain.article.service;

import com.example.nextJsCourse.domain.article.entity.Article;
import com.example.nextJsCourse.domain.article.repository.ArticleRepository;
import com.example.nextJsCourse.global.rsData.RsData;
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

    public Optional<Article> getArticle(Long id) {
        return this.articleRepository.findById(id);
    }

    public Article create(String subject, String content) {
        Article article = Article.builder()
                .subject(subject)
                .content(content)
                .build();

        this.articleRepository.save(article);

        return article;
    }

    public Article update(Long id, String subject, String content) {
        if (getArticle(id).isEmpty()) {
            return null;
        }
        Article updatedArticle = getArticle(id).get().toBuilder()
                .subject(subject)
                .content(content)
                .build();

        this.articleRepository.save(updatedArticle);

        return updatedArticle;
    }

    public RsData<String> delete(Long id) {
        if (getArticle(id).isEmpty()) {
            return RsData.of("F-1", "실패", "존재하지 않는 글");
        }
        Article deletedArticle = getArticle(id).get();
        this.articleRepository.delete(getArticle(id).get());

        return RsData.of("S-1", "성공", "ID: " + id + ", " + "제목: "
                + deletedArticle.getSubject() + ", " + "내용: " + deletedArticle.getContent());
    }
}
