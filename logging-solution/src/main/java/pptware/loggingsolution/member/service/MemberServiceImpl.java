package pptware.loggingsolution.member.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pptware.loggingsolution.member.dto.MemberDto;
import pptware.loggingsolution.member.entity.Member;
import pptware.loggingsolution.member.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional // 트랜잭션 적용 https://tecoble.techcourse.co.kr/post/2021-05-25-transactional/
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    모든 서비스 로직에서 값을 설정할 때 setter가 아닌 builder를 사용했음
    google에 @setter만 검색해도 3번째 연관검색어에 '@setter 지양'이라는 검색어가 추천됨
    이 부분에 대해서 공부해보셔도 좋고, 실제 서비스 회사에서 @setter를 잘 안쓰는 추세이기도 함
    잘 모르겠다면 @setter를 사용하셔도 무방
     */

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

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

}
