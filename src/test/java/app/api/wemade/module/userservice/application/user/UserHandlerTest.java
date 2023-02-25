package app.api.wemade.module.userservice.application.user;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import app.api.wemade.domain.DomainValidationException;
import app.api.wemade.module.userservice.domain.role.Role;
import app.api.wemade.module.userservice.domain.role.RoleName;
import app.api.wemade.module.userservice.domain.role.RoleRepository;
import app.api.wemade.module.userservice.domain.user.User;
import app.api.wemade.module.userservice.domain.user.UserRepository;
import app.api.wemade.module.userservice.domain.user.UserStatus;
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
class UserHandlerTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserHandler userHandler;
    @Captor
    ArgumentCaptor<User> userArgumentCaptor;

    @Test
    @DisplayName("회원가입 성공")
    void signupUser_Success() {
        //given
        doReturn(Optional.empty()).when(userRepository).getUserByUsername("홍길동");
        doReturn(Optional.of(new Role())).when(roleRepository)
            .getRoleByName(RoleName.ROLE_NORMAL_USER);
        // when
        userHandler.signupUser("홍길동", null, "");
        verify(userRepository).save(userArgumentCaptor.capture());
        User user = userArgumentCaptor.getValue();
        // then
        assertEquals("홍길동", user.getUsername());
    }

    @Test
    @DisplayName("같은 ID가 있을시 가입 불가능")
    void signupUser_AlreadySignUpUserId_ExceptionThrown() {
        //given
        doReturn(Optional.of(User.create("홍길동", "", ""))).when(userRepository)
            .getUserByUsername("홍길동");
        // when
        DomainValidationException e = assertThrows(DomainValidationException.class,
            () -> userHandler.signupUser("홍길동", "", ""));
        // then
        assertEquals("이미 가입된 아이디 입니다.", e.getMessage());
    }

    @Test
    @DisplayName("회원탈퇴 성공")
    void deleteUser_Success() {
        //given
        doReturn(Optional.of(User.create("홍길동", "password", "홍길동"))).when(userRepository)
            .getUserByUsername("홍길동");
        doReturn(true).when(passwordEncoder).matches("password", "password");
        // when
        userHandler.deleteUser("홍길동", "password");
        verify(userRepository).save(userArgumentCaptor.capture());
        User user = userArgumentCaptor.getValue();
        // then
        assertEquals(UserStatus.DELETED, user.getUserStatus());
    }


}