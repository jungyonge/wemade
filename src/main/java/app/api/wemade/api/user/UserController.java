package app.api.wemade.api.user;

import app.api.wemade.api.user.request.SignupRequest;
import app.api.wemade.api.user.response.SignUpDto;
import app.api.wemade.module.userservice.application.user.UserHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User API", description = "유저관련 API")
@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    private final UserHandler userHandler;

    public UserController(UserHandler signupService) {
        this.userHandler = signupService;
    }

    @Tag(name = "User API", description = "유저관련 API")
    @ApiOperation(
        value = "회원가입 요청")
    @PostMapping("/signup")
    public ResponseEntity<SignUpDto> signup(@Valid @RequestBody SignupRequest signupRequest) {

        var user = userHandler.signupUser(signupRequest.getUsername(),
            signupRequest.getPassword(),
            signupRequest.getNickname());

        return ResponseEntity.ok(new SignUpDto(user.getUsername(), user.getNickname()));
    }

    @Tag(name = "User API", description = "유저관련 API")
    @ApiOperation(
        value = "회원탈퇴 요청")
    @Secured({"ROLE_NORMAL_USER"})
    @PutMapping("/delete")
    public ResponseEntity<SignUpDto> delete(@Valid @RequestBody SignupRequest signupRequest) {

        var user = userHandler.deleteUser(signupRequest.getUsername(),
            signupRequest.getPassword());

        return ResponseEntity.ok(new SignUpDto(user.getUsername(), user.getNickname()));
    }

}
