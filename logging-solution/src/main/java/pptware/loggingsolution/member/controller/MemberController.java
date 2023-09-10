package pptware.loggingsolution.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pptware.loggingsolution.logging.TimeTrace;
import pptware.loggingsolution.member.dto.MemberDto;
import pptware.loggingsolution.member.service.MemberService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @TimeTrace
    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberDto.Post post) {
        MemberDto.Response response = memberService.createMember(post);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @TimeTrace
    @PatchMapping("/{memberId}")
    public ResponseEntity patchMember(@PathVariable("memberId") Long memberId,
                                      @RequestBody MemberDto.Patch patch) {
        MemberDto.Response response = memberService.updateMember(memberId, patch);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @TimeTrace
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
