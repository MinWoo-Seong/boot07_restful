package com.ocj1778.boot07_restful.controller;

import com.ocj1778.boot07_restful.dto.MemberRequest;
import com.ocj1778.boot07_restful.dto.MemberResponse;
import com.ocj1778.boot07_restful.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final MemberService memberService;

    //일반적인 요청 처리 메소드의 반환형 - String, void, ModelAndView
    @GetMapping("/members")
    public String getExample(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "members";
    }

    //요청 처리 메소드를 RESTful API로 작성하기 위해서는 반환형을 ResponseEntity 객체 설정
    /*
    @GetMapping("/example/members")
    public ResponseEntity<String> getExample() {
        return new ResponseEntity<>("Member Found", HttpStatus.OK);
    }
    */

    @GetMapping("/example/members")
    public ResponseEntity<List<MemberResponse>> getExample() {
        return ResponseEntity.ok(memberService.findAll());
    }

    /*
    @GetMapping("/example/members/{id}")
    public ResponseEntity<MemberResponse> getExample(@PathVariable Long id) {
        MemberResponse memberResponse=memberService.findById(id);
        if(memberResponse == null) {
            return ResponseEntity.notFound().build();
        } else {
            //return new ResponseEntity<>(memberResponse, HttpStatus.OK);
            return ResponseEntity.ok(memberResponse);
        }
    }
    */

    @GetMapping("/example/members/{id}")
    public ResponseEntity<MemberResponse> getExample(@PathVariable Long id) {
        MemberResponse memberResponse = memberService.findById(id);
        if(memberResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.DAYS))
                .body(memberResponse);
    }

    @GetMapping("/api/v0/members")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<MemberResponse> getApi() {
        return memberService.findAll();
    }

    @GetMapping("/api/v0/members/{id}")
    @ResponseBody
    public MemberResponse getApi(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @PostMapping("/api/v0/members")
    @ResponseBody
    public MemberResponse post(@RequestBody MemberRequest memberRequest) {
        return memberService.create(memberRequest);
    }
}
