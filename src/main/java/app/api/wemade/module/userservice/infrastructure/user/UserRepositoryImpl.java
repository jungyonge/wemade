package app.api.wemade.module.userservice.infrastructure.user;

import app.api.wemade.module.userservice.domain.user.User;
import app.api.wemade.module.userservice.domain.user.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<User> getUserByIdAndUsername(long id, String username) {
        return userJpaRepository.findUserByIdAndUsername(id, username);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userJpaRepository.findUserByUsername(username);
    }


}
