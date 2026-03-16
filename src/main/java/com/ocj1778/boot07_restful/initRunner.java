package com.ocj1778.boot07_restful;

import com.ocj1778.boot07_restful.model.Article;
import com.ocj1778.boot07_restful.model.Comments;
import com.ocj1778.boot07_restful.model.Member;
import com.ocj1778.boot07_restful.repository.ArticleRepository;
import com.ocj1778.boot07_restful.repository.CommentsRepository;
import com.ocj1778.boot07_restful.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class initRunner implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final CommentsRepository commentsRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (memberRepository.count() == 0) {
            var members = List.of(
                    Member.builder().name("홍길동").email("abc123@google.com").age(10).password("1234").enabled(true).build(),
                    Member.builder().name("임꺽정").email("dfg456@google.com").age(20).password("1234").enabled(true).build(),
                    Member.builder().name("전우치").email("hij789@google.com").age(30).password("1234").enabled(true).build()
            );
            memberRepository.saveAll(members);
        }

        if(articleRepository.count() == 0) {
            for(int i=0 ; i < 100 ; i++) {
                Article article = Article.builder().title("제목-"+i).description("내용-"+i)
                        .member(memberRepository.findById(i%3L+1).orElseThrow()).build();
                articleRepository.save(article);
                for(int j=0; j<10; j++) {
                    Comments comments = Comments.builder().description("댓글-"+(j+1))
                            .member(memberRepository.findById(j%3L+1).orElseThrow())
                            .article(article)
                            .build();
                    commentsRepository.save(comments);
                }

            }
        }
    }
}
