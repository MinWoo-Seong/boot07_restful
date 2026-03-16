package com.ocj1778.boot07_restful.controller;

import com.ocj1778.boot07_restful.dto.ArticleRequest;
import com.ocj1778.boot07_restful.dto.ArticleResponse;
import com.ocj1778.boot07_restful.dto.MemberRequest;
import com.ocj1778.boot07_restful.dto.MemberResponse;
import com.ocj1778.boot07_restful.service.ArticleService;
import com.ocj1778.boot07_restful.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

//@Controller
@RestController //요청 처리 메소드의 @ResponseBody 어노테이션 생략 가능
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {
    private final MemberService memberService;
    private final ArticleService articleService;

    @PostMapping
    //@ResponseBody
    @ResponseStatus(HttpStatus.CREATED) //201 상태코드로 응답
    public MemberResponse post(@RequestBody MemberRequest memberRequest) {
        return memberService.create(memberRequest);
    }

    @GetMapping
    //@ResponseBody
    public List<MemberResponse> getAll() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    //@ResponseBody
    public MemberResponse get(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @PutMapping("/{id}")
    //@ResponseBody
    public MemberResponse put(@PathVariable("id") Long id, @RequestBody MemberRequest memberRequest) {
        return memberService.update(id, memberRequest);
    }

    @PatchMapping("/{id}")
    //@ResponseBody
    public MemberResponse patch(@PathVariable("id") Long id, @RequestBody MemberRequest memberRequest) {
        return memberService.patch(id, memberRequest);
    }

    @DeleteMapping("/{id}")
    //@ResponseBody
    public void delete(@PathVariable("id") Long id) {
        memberService.deleteById(id);
    }

    @PostMapping("/{id}/articles")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleResponse postArticle(@PathVariable("id") Long id, @RequestBody ArticleRequest articleRequest) {
        return articleService.create(id, articleRequest);
    }

    //@GetMapping("/{id}/articles")
    public List<ArticleResponse> getArticles(@PathVariable("id") Long id) {
        return articleService.findByMemberId(id);
    }

    //@GetMapping("/{id}/articles")
    public void getArticles(@PathVariable("id") Long id, HttpServletResponse response) throws ServletException, IOException {
        //리다이렉트 이동 - 클라이언트에게 URI를 전달하여 API를 재요청하도록 처리
        response.sendRedirect("/api/articles?memberId="+id);
    }

    @GetMapping("/{id}/articles")
    public void getArticles(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //포워드 이동 - 서버에서 URI의 API를 요청하도록 처리
        request.getSession().getServletContext().getRequestDispatcher("/api/articles?memberId="+id).forward(request, response);
    }
}
