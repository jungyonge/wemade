package app.api.wemade.module.userservice.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    JOINED("JOINED", "가입 완료"),
    DELETED("DELETED", "회원 탈퇴"),
    ADMIN_PAUSE("ADMIN_PAUSE", "관리자 정지"),
    ;

    private final String value;
    private final String desc;
}
