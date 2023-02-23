package app.api.wemade.module.userservice.infrastructure.role;

import app.api.wemade.module.userservice.domain.role.Role;
import app.api.wemade.module.userservice.domain.role.RoleName;
import app.api.wemade.module.userservice.domain.role.RoleRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Optional<Role> getRoleByName(RoleName name) {
        return roleJpaRepository.findByName(name);
    }
}
