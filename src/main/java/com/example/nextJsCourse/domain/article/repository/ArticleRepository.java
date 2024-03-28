package com.example.nextJsCourse.domain.article.repository;

import com.example.nextJsCourse.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
