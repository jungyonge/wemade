package app.api.wemade.config.security;


import app.api.wemade.domain.DomainValidationException;
import app.api.wemade.module.userservice.domain.UserDomainValidationMessage;
import app.api.wemade.module.userservice.domain.user.User;
import app.api.wemade.module.userservice.domain.user.UserRepository;
import app.api.wemade.module.userservice.domain.user.UserStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username)
            .map(user -> {
                return createUser(username, user);
            })
            .orElseThrow(() -> new DomainValidationException(UserDomainValidationMessage.USER_NOT_FOUND));
    }

    private CustomUserDetails createUser(String username, User user) {
        if (!user.isActivated()) {
            log.error(username + " -> 활성화되어 있지 않습니다.");
            throw new DomainValidationException(UserDomainValidationMessage.USER_IS_NOT_ACTIVATED);
        }

        if (user.getUserStatus().equals(UserStatus.ADMIN_PAUSE)) {
            log.error(username + " -> 관리자에 의해 계정 정지가 되어 있습니다.");
            throw new DomainValidationException(UserDomainValidationMessage.USER_ADMIN_PAUSE);
        }

        if (user.getUserStatus().equals(UserStatus.DELETED)) {
            log.error(username + " -> 회원 탈퇴한 ID 입니다.");
            throw new DomainValidationException(UserDomainValidationMessage.USER_ALREADY_DELETE);
        }

        List<UserRole> roles = user.getRoles().stream()
            .map(role -> new UserRole(role.getId(), role.getName(), role.getCreated()))
            .collect(Collectors.toCollection(ArrayList::new));

        return new CustomUserDetails(user.getId(), user.getUsername(), user.getNickname(), "",
            roles);
    }
}
