package app.api.wemade.api.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "회원가입 요청 request")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    @Schema(description = "회원가입 요청 ID", example = "honggildong")
    @NotNull
    private String username;

    @Schema(description = "패스워드", example = "honggildong")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;

    @Schema(description = "닉네임", example = "홍길동")
    @NotNull
    private String nickname;

}
