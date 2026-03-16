package com.ocj1778.boot07_restful.service;

import com.ocj1778.boot07_restful.dto.ArticleRequest;
import com.ocj1778.boot07_restful.dto.ArticleResponse;
import com.ocj1778.boot07_restful.exception.NotFoundException;
import com.ocj1778.boot07_restful.model.Article;
import com.ocj1778.boot07_restful.model.Member;
import com.ocj1778.boot07_restful.repository.ArticleRepository;
import com.ocj1778.boot07_restful.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public ArticleResponse create(Long memberId, ArticleRequest articleRequest) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundException::new);
        Article article = Article.builder()
                .title(articleRequest.getTitle())
                .description(articleRequest.getDescription())
                .member(member).build();
        articleRepository.save(article);
        return mapToArticleResponse(article);
    }

    public List<ArticleResponse> findAll() {
        return articleRepository.findAll()
                .stream()
                .map(this::mapToArticleResponse)
                .toList();
    }

    public List<ArticleResponse> findByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundException::new);
        return articleRepository.findAllByMember(member)
                .stream()
                .map(this::mapToArticleResponse)
                .toList();
    }

    public ArticleResponse findById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapToArticleResponse(article);
    }

    public ArticleResponse update(Long id, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(id).orElseThrow(NotFoundException::new);
        article.setTitle(articleRequest.getTitle());
        article.setDescription(articleRequest.getDescription());
        articleRepository.save(article);
        return mapToArticleResponse(article);
    }

    public void delete(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(NotFoundException::new);
        articleRepository.delete(article);
    }

    private ArticleResponse mapToArticleResponse(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .description(article.getDescription())
                .created(article.getCreated())
                .updated(article.getUpdated())
                .memberId(article.getMember().getId())
                .name(article.getMember().getName())
                .email(article.getMember().getEmail()).build();
    }
}
