package com.example.nextJsCourse.domain.article.entity;

import com.example.nextJsCourse.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    @ToString(callSuper = true)
    public class Article extends BaseEntity {
        private String subject;
        private String content;
    }