package com.ocj1778.boot07_restful.service;

import com.ocj1778.boot07_restful.dto.ArticleRequest;
import com.ocj1778.boot07_restful.dto.ArticleResponse;
import com.ocj1778.boot07_restful.dto.CommentsRequest;
import com.ocj1778.boot07_restful.dto.CommentsResponse;
import com.ocj1778.boot07_restful.exception.NotFoundException;
import com.ocj1778.boot07_restful.model.Article;
import com.ocj1778.boot07_restful.model.Comments;
import com.ocj1778.boot07_restful.model.Member;
import com.ocj1778.boot07_restful.repository.ArticleRepository;
import com.ocj1778.boot07_restful.repository.CommentsRepository;
import com.ocj1778.boot07_restful.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public CommentsResponse create(Long memberId, Long articleId, CommentsRequest commentsRequest) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundException::new);
        Article article = articleRepository.findById(articleId).orElseThrow(NotFoundException::new);
        Comments comments = Comments.builder()
                .description(commentsRequest.getDescription())
                .member(member)
                .article(article)
                .build();
        commentsRepository.save(comments);
        return mapToCommentsResponse(comments);
    }

    public List<CommentsResponse> findByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundException::new);
        return commentsRepository.findAllByMember(member)
                .stream()
                .map(this::mapToCommentsResponse)
                .toList();
    }

    public List<CommentsResponse> findByArticleId(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(NotFoundException::new);
        return commentsRepository.findAllByArticle(article)
                .stream()
                .map(this::mapToCommentsResponse)
                .toList();
    }

    public CommentsResponse findById(Long id) {
        Comments comments = commentsRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapToCommentsResponse(comments);
    }

    public CommentsResponse update(Long id, CommentsRequest commentsRequest) {
        Comments comments = commentsRepository.findById(id).orElseThrow(NotFoundException::new);
        comments.setDescription(commentsRequest.getDescription());
        commentsRepository.save(comments);
        return mapToCommentsResponse(comments);
    }

    public void delete(Long id) {
        Comments comments = commentsRepository.findById(id).orElseThrow(NotFoundException::new);
        commentsRepository.delete(comments);
    }

    private CommentsResponse mapToCommentsResponse(Comments comments) {
        return CommentsResponse.builder()
                .id(comments.getId())
                .description(comments.getDescription())
                .created(comments.getCreated())
                .updated(comments.getUpdated())
                .memberId(comments.getMember().getId())
                .name(comments.getMember().getName())
                .email(comments.getMember().getEmail())
                .articleId(comments.getArticle().getId())
                .build();
    }
}
