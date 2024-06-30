package com.halbu.spring_start.service;

import com.halbu.spring_start.domain.Member;
import com.halbu.spring_start.repository.MemberRepository;
import com.halbu.spring_start.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원가입
     */
    public Long join(Member member) {
        VaidateDuplicateMember(member); //중복 회원 검사
        memberRepository.save(member);
        return member.getId();
    }

    private void VaidateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
