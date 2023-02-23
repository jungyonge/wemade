package app.api.wemade.module.userservice.infrastructure.user;

import app.api.wemade.module.userservice.domain.user.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByIdAndUsername(long id, String username);

    Optional<User> findUserByUsername(String username);


}
