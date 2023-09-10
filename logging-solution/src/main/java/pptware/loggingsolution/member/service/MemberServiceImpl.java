package pptware.loggingsolution.member.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pptware.loggingsolution.logging.TimeTrace;
import pptware.loggingsolution.member.dto.MemberDto;
import pptware.loggingsolution.member.entity.Member;
import pptware.loggingsolution.member.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @TimeTrace
    public MemberDto.Response createMember(MemberDto.Post post) {

        Member member = Member.builder()
                              .loginId(post.getLoginId())
                              .password(post.getPassword())
                              .nickName(post.getNickName())
                              .createdAt(LocalDateTime.now())
                              .build();

        Member saveMember = memberRepository.save(member);

        MemberDto.Response response = MemberDto.Response.builder()
                                                        .loginId(saveMember.getLoginId())
                                                        .password(saveMember.getPassword())
                                                        .nickName(saveMember.getNickName())
                                                        .createdAt(saveMember.getCreatedAt())
                                                        .build();

        return response;
    }

    @TimeTrace
    public MemberDto.Response updateMember(Long memberId, MemberDto.Patch patch) {

        Optional<Member> member = memberRepository.findById(memberId);

        Member updateMember = Member.builder()
                                    .memberId(memberId)
                                    .loginId(member.get().getLoginId())
                                    .password(patch.getPassword())
                                    .nickName(patch.getNickName())
                                    .createdAt(member.get().getCreatedAt())
                                    .modifiedAt(LocalDateTime.now())
                                    .build();

        Member updatedMember = memberRepository.save(updateMember);

        MemberDto.Response response = MemberDto.Response.builder()
                                                        .loginId(updatedMember.getLoginId())
                                                        .password(updatedMember.getPassword())
                                                        .nickName(updatedMember.getNickName())
                                                        .createdAt(updatedMember.getCreatedAt())
                                                        .modifiedAt(updatedMember.getModifiedAt())
                                                        .build();

        return response;
    }

    @TimeTrace
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

}
