package sports_betting;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserService {

    private final AppUserRepository userRepo;

    public AppUserService(AppUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public void updateDisplayName(Long userId, String newDisplayName) {
        if (newDisplayName == null || newDisplayName.trim().isEmpty()) {
            throw new IllegalArgumentException("Display name cannot be empty");
        }

        var user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        var existingUser = userRepo.findByDisplayName(newDisplayName.trim());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(userId)) {
            throw new IllegalArgumentException("Display name is already taken");
        }

        user.setDisplayName(newDisplayName.trim());
        userRepo.save(user);
    }
}
