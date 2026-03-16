package com.ocj1778.boot07_restful.repository;

import com.ocj1778.boot07_restful.model.Article;
import com.ocj1778.boot07_restful.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByMember(Member member);
}
