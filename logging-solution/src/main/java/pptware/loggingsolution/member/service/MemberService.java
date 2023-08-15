package pptware.loggingsolution.member.service;

import pptware.loggingsolution.member.dto.MemberDto;


// Service는 인터페이스와 구현체로 분리시켜 놓음 https://velog.io/@suhongkim98/%EC%84%9C%EB%B9%84%EC%8A%A4-%EA%B5%AC%ED%98%84-%EC%8B%9C-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94-%EC%9D%B4%EC%9C%A0-spring-AOP
public interface MemberService {

    MemberDto.Response createMember(MemberDto.Post post);

    MemberDto.Response updateMember(Long memberId, MemberDto.Patch patch);

    void deleteMember(Long memberId);

}
