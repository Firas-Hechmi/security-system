package backend.security.dashboard.repository;

import backend.security.dashboard.model.ERole;
import backend.security.dashboard.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findRoleByName(ERole name);
}
