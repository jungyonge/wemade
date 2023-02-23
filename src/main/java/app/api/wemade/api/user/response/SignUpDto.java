package app.api.wemade.api.user.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "회원가입 성공 response")
@Getter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class SignUpDto {

    @Schema(description = "id")
    @NotNull
    private String username;

    @Schema(description = "닉네임")
    @NotNull
    private String nickname;

}
