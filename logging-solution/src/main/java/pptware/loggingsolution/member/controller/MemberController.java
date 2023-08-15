package pptware.loggingsolution.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pptware.loggingsolution.member.dto.MemberDto;
import pptware.loggingsolution.member.service.MemberService;

@RestController // @Controller + @ResponseBody https://mangkyu.tistory.com/49
@RequestMapping("/members") // 스프링MVC는 FrontController 방식을 사용하기 때문에 경로 지정 https://backendcode.tistory.com/227
@RequiredArgsConstructor // 생성자 주입 방식 중 하나 final이나 @NotNull이 붙은 필드의 생성자를 자동 생성 17번째 줄에서 생성자를 생성하지 않은 이유 https://velog.io/@developerjun0615/Spring-RequiredArgsConstructor-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%9C-%EC%83%9D%EC%84%B1%EC%9E%90-%EC%A3%BC%EC%9E%85
public class MemberController {

    private final MemberService memberService;

    /*
    회원가입 로직
    12번째줄에서 HTTP Method를 쓰지않고 @PostMapping을 사용함
     */
    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberDto.Post post) {
        MemberDto.Response response = memberService.createMember(post);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*
    회원정보 수정 로직
    12번째줄에서 HTTP Method를 쓰지않고 @PatchMapping을 사용함
    */
    @PatchMapping("/{memberId}")
    public ResponseEntity patchMember(@PathVariable("memberId") Long memberId,
                                      @RequestBody MemberDto.Patch patch) {
        MemberDto.Response response = memberService.updateMember(memberId, patch);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    회원탈퇴 로직
    12번째줄에서 HTTP Method를 쓰지않고 @DeleteMapping을 사용함
    */
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
