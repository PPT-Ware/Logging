package pptware.loggingsolution.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pptware.loggingsolution.member.entity.Member;

import java.util.Optional;


    /*
    ElasticSearch와 JPA를 동시에 상속받으면 충돌이 발생함
    왜인지는 좀 더 찾아봐야 할 것 같음..
    */
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById (Long memberId);

    void  deleteById(Long memberId);
}
