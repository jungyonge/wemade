package app.api.wemade.api.user.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "회원가입 성공 response")
@Getter
@AllArgsConstructor
public class UserDto {

    @Schema(description = "회원가입 ID")
    @NotNull
    private String userId;

    @Schema(description = "이름")
    @NotNull
    private String name;

    @Schema(description = "결정세액", example = "100,000")
    @JsonProperty("결정세액")
    public String determinedTaxAmount;

    @Schema(description = "퇴직연금세액공제", example = "200,000")
    @JsonProperty("퇴직연금세액공제")
    public String retirementPensionTaxCredit;
}
