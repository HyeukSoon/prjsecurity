package com.hyeuksp.pjtsecurity.repository.tb;
import com.hyeuksp.pjtsecurity.entity.tb.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
/***
 *
 *  JpaRepository 알아보기
 * JpaRepository는 제네릭 타입 매개변수인 T와 ID를 사용합니다.
 * T는 레파지토리가 조작할 JPA 엔티티 타입을 의미하고,
 * ID는 엔티티의 PK(일반적으로 Long 또는 Integer) 타입을 의미합니다.
 * 데이터베이스에서 회원 테이블의 PK는 bigint이므로 자바에서는 Long 타입으로 선언했고,
 * 이에 따라 JpaRepository의 ID로 전달하는 값은  Member 엔티티의 ID인 Long 타입이 됩니다.
 * 아래 코드는 JpaRepository의 전체 소스 코드입니다. 자세한 건 점차 기능을 붙여나가는 과정에서 하나씩 알아보도록 하겠습니다.
 *
 */