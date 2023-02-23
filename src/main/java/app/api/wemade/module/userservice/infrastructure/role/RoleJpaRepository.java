package app.api.wemade.module.userservice.infrastructure.role;


import app.api.wemade.module.userservice.domain.role.Role;
import app.api.wemade.module.userservice.domain.role.RoleName;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RoleJpaRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(RoleName name);
}
