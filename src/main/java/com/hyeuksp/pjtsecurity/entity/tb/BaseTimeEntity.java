package com.hyeuksp.pjtsecurity.entity.tb;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

    @Column(name = "created_date")
    private LocalDateTime createdDate;   // 생성일시

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;  // 최종 수정일시

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }

}

/***
 * 6-5) BaseTimeEntity 핵심 어노테이션 알아보기
 * 어노테이션
 * 설명
 *
 * @MappedSuperclass
 * BaseTimeEntity가 JPA 엔티티의 공통 매핑 정보를 포함하는 클래스임을 의미합니다. 쉽게 말해 BaseTimeEntity에 선언된 필드들은 어떠한 엔티티에서든 상속하여 사용할 수 있으며, 이 어노테이션을 통해 코드 중복을 방지하고 매핑 정보를 재사용할 수 있습니다.
 *
 *
 * @PrePersist
 * JPA 엔티티가 저장(Insert)되기 전에 실행할 메서드를 지정합니다. 여기서는 엔티티가 데이터베이스에 최초로 생성되는 시점에 prePersist( ) 메서드가 호출되며, 이를 통해 엔티티의 생성일시를(created_date)를 자동으로 관리합니다.
 *
 *
 * @PreUpdate
 * JPA 엔티티가 수정(Update)되기 전에 실행할 메서드를 지정합니다. 여기서는 엔티티가 데이터베이스에 업데이트될 때 preUpdate( ) 메서드가 호출되며, 이를 통해 엔티티의 최종 수정일시(modified_date)를 자동으로 관리합니다.
 *
 */