package com.example.article11.repository;

import com.example.article11.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    Optional<ArticleEntity> findById (Long id);

    List<ArticleEntity> findByOrderByLocalDateTimeDesc();

    ArticleEntity save(ArticleEntity articleEntity);

    void deleteById(Long id);

    List<ArticleEntity> findByContentsContaining(String contents);



}
