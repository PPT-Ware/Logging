package pptware.loggingsolution.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        private String loginId;

        private String password;

        private String nickName;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

//        private Long memberId;

        private String password;

        private String nickName;

        private LocalDateTime modifiedAt;

    }

    @Getter
    public static class Response {

        private String loginId;

        private String password;

        private String nickName;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

        @Builder
        public Response(String loginId, String password, String nickName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
            this.loginId = loginId;
            this.password = password;
            this.nickName = nickName;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
        }
    }

}
