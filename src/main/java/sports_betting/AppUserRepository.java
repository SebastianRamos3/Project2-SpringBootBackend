package sports_betting;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByDisplayName(String displayName);

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);
}