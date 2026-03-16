package com.ocj1778.boot07_restful.controller;

import com.ocj1778.boot07_restful.dto.ArticleRequest;
import com.ocj1778.boot07_restful.dto.ArticleResponse;
import com.ocj1778.boot07_restful.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public List<ArticleResponse> getArticles(@RequestParam(name = "memberId", required = false) Long memberId) {
        if(memberId == null) {
            return articleService.findAll();//전체 게시글 목록 조회
        } else {
            return articleService.findByMemberId(memberId);//전달받은 회원 아이디의 게시글 목록 조회
        }
    }

    @GetMapping("/{id}")
    public ArticleResponse get(@PathVariable("id") Long id) {
        return articleService.findById(id);
    }

    @PutMapping("/{id}")
    public ArticleResponse put(@PathVariable("id") Long id, @RequestBody ArticleRequest articleRequest) {
        return articleService.update(id, articleRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        articleService.delete(id);
    }
}
