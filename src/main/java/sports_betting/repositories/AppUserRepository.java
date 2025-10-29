package sports_betting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sports_betting.models.AppUser;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByUsernameIgnoreCase(String username);

    Optional<AppUser> findByEmail(String email);
}