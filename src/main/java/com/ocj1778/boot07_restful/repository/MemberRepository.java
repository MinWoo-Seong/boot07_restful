package com.ocj1778.boot07_restful.repository;

import com.ocj1778.boot07_restful.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
