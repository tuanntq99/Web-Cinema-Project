package WebCinema.Repository;

import WebCinema.Entity.Enum.ERole;
import WebCinema.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    //    Optional trong truong hop rong
    Optional<Role> findByRoleName(ERole roleName);
}
