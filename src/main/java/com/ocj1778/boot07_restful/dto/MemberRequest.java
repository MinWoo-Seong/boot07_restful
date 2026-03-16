package com.ocj1778.boot07_restful.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberRequest {
    private String name;
    private String email;
    private Integer age;
    private String password;
    private Boolean enabled;
}
