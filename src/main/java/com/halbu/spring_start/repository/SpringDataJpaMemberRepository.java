package com.halbu.spring_start.repository;

import com.halbu.spring_start.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL select m from Member M where m.name = ? 로 자동 치환해줌
    @Override
    Optional<Member> findByName(String name);
}
