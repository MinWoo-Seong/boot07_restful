package com.ocj1778.boot07_restful.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberResponse {
    private Long id;
    private String name;
    private String email;
    private Integer age;
}
