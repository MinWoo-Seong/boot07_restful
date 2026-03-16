package com.ocj1778.boot07_restful.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;

    private Long memberId;
    private String name;
    private String email;
}
