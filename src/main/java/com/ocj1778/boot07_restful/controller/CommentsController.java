package com.ocj1778.boot07_restful.controller;

import com.ocj1778.boot07_restful.dto.CommentsRequest;
import com.ocj1778.boot07_restful.dto.CommentsResponse;
import com.ocj1778.boot07_restful.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentsService commentsService;

    @GetMapping("/articles/{articleId}/comments")
    public List<CommentsResponse> getCommentsByArticle(@PathVariable(name = "articleId") Long articleId) {
        return commentsService.findByArticleId(articleId);
    }

    @GetMapping("/articles/{articleId}/comments/{id}")
    public CommentsResponse getCommentsById(@PathVariable("id") Long id) {
        return commentsService.findById(id);
    }

    @PutMapping("/articles/{articleId}/comments/{id}")
    public CommentsResponse put(@PathVariable("id") Long id, @RequestBody CommentsRequest commentsRequest) {
        return commentsService.update(id, commentsRequest);
    }

    @DeleteMapping("/articles/{articleId}/comments/{id}")
    public void delete(@PathVariable("id") Long id) {
        commentsService.delete(id);
    }

    @GetMapping("/members/{memberId}/comments")
    public List<CommentsResponse> getCommentsByMember(@PathVariable(name = "memberId") Long memberId) {
        return commentsService.findByMemberId(memberId);
    }
}
