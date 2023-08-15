package pptware.loggingsolution.member.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id // 기본 키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 DB에 위임 https://rutgo-letsgo.tistory.com/306
    private Long memberId;

    @Column(nullable = false, unique = true, updatable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickName;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime modifiedAt;


    @Builder
    public Member(Long memberId, String loginId, String password, String nickName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    this.memberId = memberId;
    this.loginId = loginId;
    this.password = password;
    this.nickName = nickName;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
    }
}

