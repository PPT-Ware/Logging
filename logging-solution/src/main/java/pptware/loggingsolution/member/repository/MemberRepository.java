package pptware.loggingsolution.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pptware.loggingsolution.logging.TimeTrace;
import pptware.loggingsolution.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @TimeTrace
    Optional<Member> findById (Long memberId);

    @TimeTrace
    void  deleteById(Long memberId);
}
