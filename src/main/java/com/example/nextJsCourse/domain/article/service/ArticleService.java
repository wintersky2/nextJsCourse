package com.example.nextJsCourse.domain.article.service;

import com.example.nextJsCourse.domain.article.entity.Article;
import com.example.nextJsCourse.domain.article.repository.ArticleRepository;
import com.example.nextJsCourse.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public RsData create(String subject, String content) {
        Article article = Article.builder()
                .subject(subject)
                .content(content)
                .build();
        try {
            this.articleRepository.save(article);
        } catch (Exception error) {
            return RsData.of("F-3", "실패", error);
        }
        return RsData.of("S-3", "성공", article);
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public RsData<Article> update(Article article, String subject, String content) {
        article.setSubject(subject);
        article.setContent(content);
        articleRepository.save(article);

        return RsData.of(
                "S-4",
                "%d번 게시물이 수정 되었습니다.".formatted(article.getId()),
                article
        );
    }

    @Transactional
    public RsData delete(Article article) {
        try{
            this.articleRepository.delete(article);
        }catch (Exception error){
            return RsData.of("F-5","실패",error);
        }

        return RsData.of("S-5", "성공", "ID: " + article.getId() + ", " + "제목: "
                + article.getSubject() + ", " + "내용: " + article.getContent());
    }
}
