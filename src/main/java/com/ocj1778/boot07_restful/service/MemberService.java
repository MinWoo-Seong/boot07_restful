package com.ocj1778.boot07_restful.service;

import com.ocj1778.boot07_restful.dto.MemberRequest;
import com.ocj1778.boot07_restful.dto.MemberResponse;
import com.ocj1778.boot07_restful.exception.NotFoundException;
import com.ocj1778.boot07_restful.model.Member;
import com.ocj1778.boot07_restful.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse create(MemberRequest memberRequest) {
        /*
        Member member = Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .age(memberRequest.getAge())
                .password(memberRequest.getPassword())
                .enabled(memberRequest.getEnabled())
                .enabled(true).build();
         */
        Member member = mapToMember(memberRequest);

        memberRepository.save(member);
        return mapToMemberResponse(member);
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapToMemberResponse(member);
    }

    public List<MemberResponse> findAll() {
        return memberRepository
                .findAll()
                .stream()
                .map(this::mapToMemberResponse)
                .toList();
    }

    @Transactional
    public MemberResponse update(Long id, MemberRequest memberRequest) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        // password, enabled 필드를 유지해야 하므로 나머지 항목들만 업데이트
        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
        member.setAge(memberRequest.getAge());
        //member.setPassword(memberRequest.getPassword());
        //member.setEnabled(memberRequest.getEnabled());
        memberRepository.save(member);
        return mapToMemberResponse(member);
    }

    @Transactional
    public MemberResponse patch(Long id, MemberRequest memberRequest) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        // 전달된 값이 있는 필드만 업데이트
        if (memberRequest.getName() != null) member.setName(memberRequest.getName());
        if (memberRequest.getEmail() != null) member.setEmail(memberRequest.getEmail());
        if (memberRequest.getAge() != null) member.setAge(memberRequest.getAge());
        if (memberRequest.getPassword() != null) member.setPassword(memberRequest.getPassword());
        if (memberRequest.getEnabled() != null) member.setEnabled(memberRequest.getEnabled());
        memberRepository.save(member);
        return mapToMemberResponse(member);
    }

    @Transactional
    public void deleteById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        memberRepository.delete(member);
    }

    private MemberResponse mapToMemberResponse(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .age(member.getAge())
                .build();
    }

    private Member mapToMember(MemberRequest memberRequest) {
        return Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .age(memberRequest.getAge())
                .password(memberRequest.getPassword())
                .enabled(memberRequest.getEnabled())
                .enabled(true).build();
    }
}
