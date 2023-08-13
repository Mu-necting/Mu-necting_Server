package com.munecting.server.domain.archive.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ArchiveTest {
    @PersistenceContext
    EntityManager em;

    @Test
    public void QArchive(){
        Archive archive1 = new Archive();
        em.persist(archive1);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QArchive qArchive = QArchive.archive;
        Archive result = query
                .selectFrom(qArchive)
                .fetchOne();

        assertThat(result).isEqualTo(archive1); //Querydsl Q타입이 정상 동작하는가?
        assertThat(result.getId()).isEqualTo(archive1.getId());//롬복 @Getter확인
    }

}