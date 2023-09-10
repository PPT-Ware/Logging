package pptware.loggingsolution.member.service;

import pptware.loggingsolution.member.dto.MemberDto;

public interface MemberService {

    MemberDto.Response createMember(MemberDto.Post post);

    MemberDto.Response updateMember(Long memberId, MemberDto.Patch patch);

    void deleteMember(Long memberId);

}
