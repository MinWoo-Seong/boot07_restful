package com.ocj1778.boot07_restful.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentsResponse {
    private Long id;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;

    private Long memberId;
    private String name;
    private String email;

    private Long articleId;
}
