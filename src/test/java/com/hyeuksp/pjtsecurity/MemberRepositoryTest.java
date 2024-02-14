package com.hyeuksp.pjtsecurity;

import com.hyeuksp.pjtsecurity.domain.tb.Gender;
import com.hyeuksp.pjtsecurity.entity.tb.MemberEntity;
import com.hyeuksp.pjtsecurity.repository.tb.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    // 회원 정보 생성
    @Test
     void saveMember() {
        MemberEntity saveParams = MemberEntity.builder()
                .loginId("dyl6266")
                .password("1234")
                .name("도뎡")
                .gender(Gender.M)
                .birthday(LocalDate.of(1994, 9, 12))
                .deleteYn(false)
                .build();

        MemberEntity member = memberRepository.save(saveParams);
        Assertions.assertEquals(member.getLoginId(), "dyl6266");
    }

    // 전체 회원 조회
    @Test
    void findAllMember() {
        memberRepository.findAll();
    }

    // 회원 상세정보 조회
    @Test
    void findMemberById() {
        MemberEntity member = memberRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException());
        Assertions.assertEquals(member.getLoginId(), "dyl6266");
    }

    // 회원 정보 삭제
    @Test
    void deleteMemberById() {
        memberRepository.deleteById(1L);
    }

}