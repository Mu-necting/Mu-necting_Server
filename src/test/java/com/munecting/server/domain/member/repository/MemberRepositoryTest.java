package com.munecting.server.domain.member.repository;

import com.munecting.server.domain.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Transactional
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Test
    public void testJPA(){
        Member m = new Member("m1");
        memberRepository.save(m);
        Optional<Member> result = memberRepository.findById(1L);
        log.info("member = "+result.get());
    }

}