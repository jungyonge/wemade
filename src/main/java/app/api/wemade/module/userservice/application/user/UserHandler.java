package app.api.wemade.module.userservice.application.user;

import app.api.wemade.domain.DomainValidationException;
import app.api.wemade.module.userservice.domain.UserDomainValidationMessage;
import app.api.wemade.module.userservice.domain.role.RoleName;
import app.api.wemade.module.userservice.domain.role.RoleRepository;
import app.api.wemade.module.userservice.domain.user.User;
import app.api.wemade.module.userservice.domain.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserHandler {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserHandler(UserRepository userRepository, RoleRepository roleRepository,
        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public User signupUser(String username, String password, String nickname) {

        if (userRepository.getUserByUsername(username).isPresent()) {
            throw new DomainValidationException(UserDomainValidationMessage.USER_ID_ALREADY_EXIST);
        }

        var user = User.create(username, passwordEncoder.encode(password), nickname);

        var normalUser = roleRepository.getRoleByName(RoleName.ROLE_NORMAL_USER)
            .orElseThrow(() -> new DomainValidationException(
                UserDomainValidationMessage.ROLE_DOES_NOT_EXIST));
        user.addRole(normalUser);

        return userRepository.save(user);
    }

    @Transactional
    public User deleteUser(String username, String password) {
        return userRepository.getUserByUsername(username).map(user -> {

            if (passwordEncoder.matches(password, user.getPassword())) {
                user.deleteUser();
                userRepository.save(user);
            } else {
                throw new DomainValidationException(
                    UserDomainValidationMessage.USER_NOT_FOUND_OR_PASSWORD_WRONG);
            }
            return user;
        }).orElseThrow(() -> new DomainValidationException(
            UserDomainValidationMessage.USER_NOT_FOUND_OR_PASSWORD_WRONG));
    }
}
