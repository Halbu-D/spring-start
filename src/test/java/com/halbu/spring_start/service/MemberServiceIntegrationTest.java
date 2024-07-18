package com.halbu.spring_start.service;

import com.halbu.spring_start.domain.Member;
import com.halbu.spring_start.repository.MemberRepository;
import com.halbu.spring_start.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // commit을 해야 db에 반영이 되는데, 테스트가 끝난 후 rollback 해줌
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("kotlin");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("Tester");

        Member member2 = new Member();
        member2.setName("Tester");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// member2가 들어가면 예외가 터져야함

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try {
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){ //예외가 터져서 정상적으로 실행됨
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");//오류 메세지가 같아야 함
        }
*/

        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}