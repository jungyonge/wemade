package app.api.wemade.module.userservice.domain;


import app.api.wemade.domain.ExplainableMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserDomainValidationMessage implements ExplainableMessage {

    USER_ID_ALREADY_EXIST(2_0001, "이미 가입된 아이디 입니다."),
    ROLE_ALREADY_EXIST(2_0002, "이미 존재하는 권한입니다."),
    ROLE_DOES_NOT_EXIST(2_0003, "권한이 존재하지 않습니다."),
    USER_NOT_FOUND(2_0004, "회원이 존재하지 않습니다."),
    USER_NOT_FOUND_OR_PASSWORD_WRONG(2_0005, "회원이 존재하지 않거나 비밀번호가 틀렸습니다."),
    USER_ALREADY_DELETE(2_0006, "탈퇴한 회원입니다."),
    USER_IS_NOT_ACTIVATED(2_0007, "비활성화된 회원입니다."),
    USER_ADMIN_PAUSE(2_0008, "관리자에 의해 계정 정지가 되어 있습니다."),

    ;

    private final int code;
    private final String message;
    private final HttpStatus status;

    UserDomainValidationMessage(int code, String message) {
        this.code = code;
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

    public int getStatus() {
        return status.value();
    }
}
