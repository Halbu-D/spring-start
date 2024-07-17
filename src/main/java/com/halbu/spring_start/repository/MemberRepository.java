package com.halbu.spring_start.repository;

import com.halbu.spring_start.domain.Member;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //java8에 추가된 기능, NULL값을 반환할때 Optional으로 감싸서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
