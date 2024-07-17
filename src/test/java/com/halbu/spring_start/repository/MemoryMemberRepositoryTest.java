package com.halbu.spring_start.repository;

import com.halbu.spring_start.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //한번에 실행 시 실행 순서는 보장하지 못함. 따라서 테스트가 끝나면 기존 데이터는 클리어 시켜야 함.
    @AfterEach //모든 메소드에 대해 실행이 끝날때마다 호출됨.
    public void afterEach(){ //각 메소드가 끝나면 호출되는 코드
        repository.clearStore();
    }

    @Test //아래 메소드를 실행할 수 있음.
    public void save() {
        Member member = new Member();
        member.setName("TestCase");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //get()쓰는 이유 -> 반환타입이 Optional이기 때문
        //Assertions.assertEquals(member, result); //출력값이 기댓값이랑 같은지 비교
        assertThat(member).isEqualTo(result); // Assertions에 option + enter로 static ipport 가능

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Test2");
        repository.save(member2);

        Member result = repository.findByName("Test1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Test2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
