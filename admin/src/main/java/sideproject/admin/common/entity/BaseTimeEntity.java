package sideproject.admin.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@ToString(of = { "createDate", "lastModifiedDate" })
public class BaseTimeEntity {

    // Java 8 date/time type java.time.LocalDateTime not supported by default
    // LocalDateTime을 역직렬화하지 못해서 생기는 문제
    // 추가적으로 만약 캐시로 사용할 객체에 LocalDateTime 타입의 값이
    // 존재한다면 위처럼 @JsonSerialize, @JsonDeserialize 어노테이션을 기입해줘야 함
    // 그렇지 않으면 오류 발생
    // 어노테이션 대신 ObjectMapper 모듈 추가 후 사용

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime lastModifiedDate;
}
