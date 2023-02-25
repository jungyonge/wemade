package app.api.wemade.module.authorizationservice.application.authorization;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import app.api.wemade.config.jwt.TokenProvider;
import app.api.wemade.domain.DomainValidationException;
import app.api.wemade.module.userservice.domain.user.User;
import app.api.wemade.module.userservice.domain.user.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthorizationHandlerTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TokenProvider tokenProvider;
    @InjectMocks
    private AuthorizationHandler authorizationHandler;
    @Captor
    ArgumentCaptor<User> userArgumentCaptor;


    @Test
    @DisplayName("로그인 성공")
    void loginUser_Success() {
        //given
        doReturn(Optional.of(User.create("홍길동", "password", "홍길동"))).when(userRepository)
            .getUserByUsername("홍길동");
        doReturn(true).when(passwordEncoder).matches("password", "password");
        // when
        authorizationHandler.loginUser("홍길동", "password");
        verify(userRepository).save(userArgumentCaptor.capture());
        User user = userArgumentCaptor.getValue();
        // then
        assertEquals("홍길동", user.getUsername());
    }

    @Test
    @DisplayName("탈퇴한 회원 로그인 실패")
    void loginUser_DeleteUser_ExceptionThrown() {
        //given
        Optional<User> givenUser = Optional.of(User.create("홍길동", "password", "홍길동"));
        givenUser.get().deleteUser();
        doReturn(givenUser).when(userRepository).getUserByUsername("홍길동");
        // when
        DomainValidationException e = assertThrows(DomainValidationException.class,
            () -> authorizationHandler.loginUser("홍길동", "password"));
        // then
        assertEquals("탈퇴한 회원입니다.", e.getMessage());
    }

    @Test
    @DisplayName("비밀번호 불일치 로그인 실패")
    void loginUser_WrongPassword_ExceptionThrown() {
        //given
        doReturn(Optional.of(User.create("홍길동","password","홍길동"))).when(userRepository).getUserByUsername("홍길동");
        doReturn(false).when(passwordEncoder).matches("password", "password");
        // when
        DomainValidationException e = assertThrows(DomainValidationException.class,
            () -> authorizationHandler.loginUser("홍길동", "password"));
        // then
        assertEquals("회원이 존재하지 않거나 비밀번호가 틀렸습니다.", e.getMessage());
    }


}